package com.jrsoft.fri.bxgl.action;

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

import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.BxglFrom;
import com.jrsoft.fri.bxgl.service.BxglSafeService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkService;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.action.Log;

public class BxglSafeAction  extends DispatchAction{
	private BxglSafeService safeService;
	private DtjkElevatorService elevatorService;
	public BxglSafeService getSafeService() {
		return safeService;
	}
	public void setSafeService(BxglSafeService safeService) {
		this.safeService = safeService;
	}
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}
	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	
	/**
	 * 查询 未保，在保，脱保 电梯列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryElevator(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String flag=request.getParameter("flag");
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from dtjk_elevator de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //维保单位
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保单位
						" where  1=1   and de.delflag!='1' " ;
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
					sql+=" and de.brand like '%"+brand+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and de.numbers = '"+numbers+"'";
				}
				if(flag.equals("1")){
					sql+=" and de.safe_State is null";
				}else if(flag.equals("2")){
					sql+=" and de.safe_State='0' ";
				}else{
					sql+=" and de.safe_State='1' ";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
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
					String sql3="select count(*)  from Bxgl_Safe de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql3);
					elevator.setNum(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("brand", brand);
				request.setAttribute("numbers", numbers);
				request.setAttribute("flag", flag);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
				if(flag.equals("1")){
					 return	new ActionForward("/jsp/Insurance/uninsured/insuranceList.jsp");
				}else if(flag.equals("2")){
					 return	new ActionForward("/jsp/Insurance/offPaul/insuranceList.jsp");
				}else{
					 return	new ActionForward("/jsp/Insurance/paul/insuranceList.jsp");
				}
		
		}
	/**
	 * 新增电梯保险记录信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		BxglFrom from=(BxglFrom)form;
		BxglSafe entity =from.getSafe();
		entity.setStartTime(df.parse(startTime));
		entity.setEndTime(df.parse(endTime));
		safeService.save(entity);
		
		//修改保险状态
		DtjkElevator elevator=elevatorService.get(entity.getElevatorId().getId());
		if(elevator.getSafeState()==null||elevator.getSafeState().equals("")){
			elevator.setSafeState("0");
			elevatorService.update(elevator);
		}
		
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加电梯保险记录", "1");
		
	    return	new ActionForward("/safeAction.do?method=query&elevatorId="+entity.getElevatorId().getId());
	}
	
	/**
	 * 查询 电梯保险记录列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		
		String num=request.getParameter("num");   //当前页
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<BxglSafe> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
						" from Bxgl_Safe de " +			
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //使用单位
						" where  1=1 " ;
				
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				if(startTime!=null&&!startTime.equals("")){
					sql+=" and de.start_Time >= to_date('" + startTime+ "','yyyy-MM-dd')";
				}
				if(endTime!=null&&!endTime.equals("")){
					sql+=" and de.end_Time <= to_date('" + endTime+ "','yyyy-MM-dd')";
				}
				
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<BxglSafe>();
				while(rs.next()){
					BxglSafe useUnit=new BxglSafe();
					useUnit.setId(rs.getLong("id"));
					useUnit.setStartTime(df.parse(rs.getString("start_Time")));
					useUnit.setEndTime(df.parse(rs.getString("end_Time")));
					useUnit.setMoney(rs.getLong("money"));
					useUnit.setState(rs.getString("state"));
					useUnit.setBeneficiary(rs.getString("beneficiary"));
					list.add(useUnit);
					
				}
				
				
				request.setAttribute("elevatorId", elevatorId);
				if(startTime!=null){
					request.setAttribute("startTime", df.parse(startTime));
				}
				if(endTime!=null){
					request.setAttribute("endTime", df.parse(endTime));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/Insurance/uninsured/SafeList.jsp");
		}
	

	/**
	 * 编辑 查看 电梯保险记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		BxglSafe list=safeService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		
		//查询图片路径
		List<String> str=new ArrayList<String>();
		if(list.getPicturePath()!=null&&!list.getPicturePath().equals("")){
			String  arr []=list.getPicturePath().split(",");
			for(int i=0;i<arr.length;i++){
				arr[i]=arr[i].replace("//", "/");
				str.add(arr[i]);
			}
			request.setAttribute("str", str);
		}
		
		
		
		
		if(flag.equals("1")){
			return	new ActionForward("/jsp/Insurance/uninsured/updateSafe.jsp");
		}else{
			return	new ActionForward("/jsp/Insurance/uninsured/detailSafe.jsp");

		}
	}
	
	/**
	 * 修改电梯保险记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		BxglFrom from=(BxglFrom)form;
		BxglSafe unit =from.getSafe();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		BxglSafe entity=safeService.get(unit.getId());
		if(entity!=null){
			entity.setMoney(unit.getMoney());
			entity.setState(unit.getState());
			entity.setElevatorId(unit.getElevatorId());
			entity.setStartTime(df.parse(startTime));
			entity.setEndTime(df.parse(endTime));
			entity.setPicturePath(unit.getPicturePath());
			safeService.update(entity);
			DtjkElevator elevator =elevatorService.get(entity.getElevatorId().getId());

			//生成 操作日志
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			Log log=new Log();
	        log.addLog(user.getName(), "修改电梯电梯保险记录，电梯注册号："+elevator.getRegisterid(), "1");

		}	
		return	new ActionForward("/safeAction.do?method=query&elevatorId="+entity.getElevatorId().getId());

	}
	
	
	
	/**
	 * 删除年检记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator entity =elevatorService.get(Long.parseLong(elevatorId));
		safeService.delete(id);
		

		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除电梯电梯保险记录，电梯注册号："+entity.getRegisterid(), "1");
		 return	new ActionForward("/serviceAction.do?method=query&elevatorId="+elevatorId);
	}
	/**
	 * 批量 删除 年检记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		String elevatorId=request.getParameter("elevatorId");

		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				DtjkElevator entity =elevatorService.get(Long.parseLong(elevatorId));
				safeService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除电梯电梯保险记录，电梯注册号："+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/serviceAction.do?method=query&elevatorId="+elevatorId);
		
	}

}
