package com.jrsoft.fri.dtjk.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;

public class DtjkElevatorAction extends DispatchAction{
	private DtjkElevatorService elevatorService;

	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	

	/**
	 * 新增 电梯信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String installTime=request.getParameter("installTime");
		String manufactureTime=request.getParameter("manufactureTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		elevator.setUserid(user);
		elevator.setManufactureTime(df.parse(manufactureTime));
		elevator.setInstallTime(df.parse(installTime));
		elevator.setState("正常");
		elevatorService.save(elevator);
	    return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 查询 电梯列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		
		 if(registerid!=null){
			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(distinguishid!=null){
			 distinguishid=new String(distinguishid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(registerid!=null&&!registerid.equals("")){
			hql+=" and registerid like '%"+registerid+"%'";
		}
		if(distinguishid!=null&&!distinguishid.equals("")){
			hql+=" and distinguishid like '%"+distinguishid+"%'";
		}
		if(useUnitName!=null&&!useUnitName.equals("")){
			hql+=" and maintenanceUnitId.name like '%"+useUnitName+"%'";
		}
		if(brand!=null&&!brand.equals("")){
			hql+=" and brand like '%"+brand+"%'";
		}
		if(numbers!=null&&!numbers.equals("")){
			hql+=" and numbers like '%"+numbers+"%'";
		}
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(brand!=null&&!brand.equals("")){
					sql+=" and de.brand de.like '%"+brand+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and de.numbers like '%"+numbers+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setState(rs.getString("state"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setInstallUnit(rs.getString("install_Unit"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					elevator.setPeriod(rs.getString("period"));
					String sql2="select count(*)  from dtjk_phone de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("brand", brand);
				request.setAttribute("numbers", numbers);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/elevator/elevatorList.jsp");
		}
	
	/**
	 * 查询 电梯列表  电梯监控
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryMonitor(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		
		 if(registerid!=null){
			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(distinguishid!=null){
			 distinguishid=new String(distinguishid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(registerid!=null&&!registerid.equals("")){
			hql+=" and registerid like '%"+registerid+"%'";
		}
		if(distinguishid!=null&&!distinguishid.equals("")){
			hql+=" and distinguishid like '%"+distinguishid+"%'";
		}
		if(useUnitName!=null&&!useUnitName.equals("")){
			hql+=" and maintenanceUnitId.name like '%"+useUnitName+"%'";
		}
		if(brand!=null&&!brand.equals("")){
			hql+=" and brand like '%"+brand+"%'";
		}
		if(numbers!=null&&!numbers.equals("")){
			hql+=" and numbers like '%"+numbers+"%'";
		}
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(brand!=null&&!brand.equals("")){
					sql+=" and de.brand de.like '%"+brand+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and de.numbers like '%"+numbers+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setState(rs.getString("state"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setInstallUnit(rs.getString("install_Unit"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("brand", brand);
				request.setAttribute("numbers", numbers);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/monitor/monitorList.jsp");
		}
	
	/**
	 * 查询 电梯列表  电梯监控
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryPlayback (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		
		 if(registerid!=null){
			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(distinguishid!=null){
			 distinguishid=new String(distinguishid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(registerid!=null&&!registerid.equals("")){
			hql+=" and registerid like '%"+registerid+"%'";
		}
		if(distinguishid!=null&&!distinguishid.equals("")){
			hql+=" and distinguishid like '%"+distinguishid+"%'";
		}
		if(useUnitName!=null&&!useUnitName.equals("")){
			hql+=" and maintenanceUnitId.name like '%"+useUnitName+"%'";
		}
		if(brand!=null&&!brand.equals("")){
			hql+=" and brand like '%"+brand+"%'";
		}
		if(numbers!=null&&!numbers.equals("")){
			hql+=" and numbers like '%"+numbers+"%'";
		}
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(brand!=null&&!brand.equals("")){
					sql+=" and de.brand de.like '%"+brand+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and de.numbers like '%"+numbers+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setState(rs.getString("state"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setInstallUnit(rs.getString("install_Unit"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					
					String sql2="select count(*)  from dtjk_record de where  1=1  and elevator_id = '"+rs.getString("registerid")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("brand", brand);
				request.setAttribute("numbers", numbers);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/playback/elevatorList.jsp");
		}
	
	/**
	 * 查询 电梯管理  电梯监控
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryManage (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		
		 if(registerid!=null){
			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(distinguishid!=null){
			 distinguishid=new String(distinguishid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(registerid!=null&&!registerid.equals("")){
			hql+=" and registerid like '%"+registerid+"%'";
		}
		if(distinguishid!=null&&!distinguishid.equals("")){
			hql+=" and distinguishid like '%"+distinguishid+"%'";
		}
		if(useUnitName!=null&&!useUnitName.equals("")){
			hql+=" and maintenanceUnitId.name like '%"+useUnitName+"%'";
		}
		if(brand!=null&&!brand.equals("")){
			hql+=" and brand like '%"+brand+"%'";
		}
		if(numbers!=null&&!numbers.equals("")){
			hql+=" and numbers like '%"+numbers+"%'";
		}
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(brand!=null&&!brand.equals("")){
					sql+=" and de.brand de.like '%"+brand+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and de.numbers like '%"+numbers+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setState(rs.getString("state"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setInstallUnit(rs.getString("install_Unit"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					
					String sql2="select count(*)  from dtjk_maintenance_records de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNumRecords(n);
					sql2="select count(*)  from dtjk_yearly_inspection de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNumYearly(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("brand", brand);
				request.setAttribute("numbers", numbers);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/manage/manageList.jsp");
		}
	/**
	 * 编辑 查看 电梯 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkElevator list=elevatorService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/elevator/updateElevator.jsp");
		}else if(flag.equals("3")){
			return	new ActionForward("/jsp/dtjk/period/period.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/elevator/detailElevator.jsp");
		}
	}
	
	/**
	 * 修改电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String installTime=request.getParameter("installTime");
		String manufactureTime=request.getParameter("manufactureTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		DtjkElevator entity =elevatorService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setRegisterid(elevator.getRegisterid());
			entity.setDistinguishid(elevator.getDistinguishid());
			entity.setBrand(elevator.getBrand());
			entity.setModel(elevator.getModel());
			entity.setState(elevator.getState());
			entity.setType(elevator.getType());
			entity.setNumbers(elevator.getNumbers());
			entity.setLabel(elevator.getLabel());
			entity.setPlace(elevator.getPlace());
			entity.setGatewayId(elevator.getGatewayId());
			entity.setUseUnitId(elevator.getUseUnitId());
			entity.setMaintenanceUnitId(elevator.getMaintenanceUnitId());
			entity.setRegisterState(elevator.getRegisterState());
			entity.setSpeed(elevator.getSpeed());
			entity.setMaintenanceUsersId(elevator.getMaintenanceUsersId());
			entity.setInstallPlace(elevator.getInstallPlace());
			entity.setInstallUnit(elevator.getInstallUnit());
			entity.setInstallUser(elevator.getInstallUser());
			entity.setManufacturer(elevator.getManufacturer());
			entity.setManufacturerPhone(elevator.getManufacturerPhone());
			entity.setManufacturerAddress(elevator.getManufacturerAddress());
			entity.setManufacturerUrl(elevator.getManufacturerUrl());
			entity.setFilialeAddress(elevator.getFilialeAddress());
			entity.setFilialePhone(elevator.getFilialePhone());
			entity.setFilialeContact(elevator.getFilialeContact());
			entity.setServiceIfe(elevator.getServiceIfe());
			entity.setRemarks(elevator.getRemarks());
			
			entity.setInstallTime(df.parse(installTime));
			entity.setManufactureTime(df.parse(manufactureTime));
			elevatorService.update(entity);
		}
		
		return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	/**
	 * 修改电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updatePeriod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		DtjkElevator entity =elevatorService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setPeriod(elevator.getPeriod());
			elevatorService.update(entity);
		}
		
		return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	
	
	/**
	 * 删除电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		elevatorService.delete(id);
		 return	new ActionForward("/elevatorAction.do?method=query");
	}
	/**
	 * 批量 删除 电梯
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				elevatorService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/elevatorAction.do?method=query");
		
	}
	
	/**
	 * 导出 电梯信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String register=request.getParameter("registerid");
		String distinguish=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		DtjkElevator elevator=new DtjkElevator();
		
		 if(register!=null){
			 register=new String(register.getBytes("iso-8859-1"),"utf-8");
			 elevator.setRegisterid(register);
		 }
		 if(distinguish!=null){
			 distinguish=new String(distinguish.getBytes("iso-8859-1"),"utf-8");
			 elevator.setDistinguishid(distinguish);
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
			 elevator.setUseUnitName(useUnitName);
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
			 elevator.setBrand(brand);
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
			 elevator.setNumbers(numbers);
		 }
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "电梯信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			elevatorService.export(filePath, elevator);

			// 下载excel
			BufferedOutputStream bos = null;
			StringBuffer sb = new StringBuffer(50);
			sb.append("attachment;   filename=");
			sb.append(fileName);
			// System.out.println("----------sb="+sb);

			if (null != filePath && fileName != null) {
				response.setContentType("application/x-msdownload;charset=GBK");
				response.setHeader("Content-Disposition", new String(sb
						.toString().getBytes(), "ISO8859-1"));
				FileInputStream fis = new FileInputStream(filePath);
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buffer = new byte[2048];
				while (fis.read(buffer) != -1) {
					bos.write(buffer);
				}
				bos.write(buffer, 0, buffer.length);
				fis.close();
				bos.close();
			}
			File fs = new File(filePath);
			if (fs.isFile() && fs.exists()) {
				fs.delete();
				System.out.println("删除单个文件" + fileName + "成功！");
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	
		 return	null;
		
	}
	
	/**
	 * 选择 电梯列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		String id2=request.getParameter("id2");
		
		String registerid=request.getParameter("registerid");
		String installPlace=request.getParameter("installPlace");
		String useUnitName=request.getParameter("useUnitName");
		
		 if(registerid!=null){
			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(installPlace!=null){
			 installPlace=new String(installPlace.getBytes("iso-8859-1"),"utf-8");
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(registerid!=null&&!registerid.equals("")){
			hql+=" and registerid like '%"+registerid+"%'";
		}
		if(installPlace!=null&&!installPlace.equals("")){
			hql+=" and installPlace like '%"+installPlace+"%'";
		}
		if(useUnitName!=null&&!useUnitName.equals("")){
			hql+=" and maintenanceUnitId.name like '%"+useUnitName+"%'";
		}
		
		hql+="order by id ";
		List<DtjkElevator> DtjkElevators=elevatorService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(DtjkElevators.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(installPlace!=null&&!installPlace.equals("")){
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkElevator>();
				while(rs.next()){
					DtjkElevator elevator=new DtjkElevator();
					elevator.setId(rs.getLong("id"));
					elevator.setRegisterid(rs.getString("registerid"));
					elevator.setDistinguishid(rs.getString("distinguishid"));
					elevator.setBrand(rs.getString("brand"));
					elevator.setState(rs.getString("state"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setLabel(rs.getString("label"));
					elevator.setInstallUnit(rs.getString("install_Unit"));
					elevator.setInstallPlace(rs.getString("install_Place"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					list.add(elevator);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("id2", id2);
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/comm/selectElevator.jsp");
		}
}
