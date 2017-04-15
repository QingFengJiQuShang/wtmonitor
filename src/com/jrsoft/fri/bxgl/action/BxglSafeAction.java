package com.jrsoft.fri.bxgl.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
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
import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
import com.jrsoft.fri.bxgl.service.BxglClaimService;
import com.jrsoft.fri.bxgl.service.BxglSafeService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkService;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.xtgl.entity.XtglSafeUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.service.XtglSafeUnitService;
import com.jrsoft.fri.xtsz.action.Log;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class BxglSafeAction  extends DispatchAction{
	private BxglSafeService safeService;
	private DtjkElevatorService elevatorService;
	private BxglClaimService claimService ;
	private XtglSafeUnitService safeUnitService;
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
	
	public BxglClaimService getClaimService() {
		return claimService;
	}
	public void setClaimService(BxglClaimService claimService) {
		this.claimService = claimService;
	}
	public XtglSafeUnitService getSafeUnitService() {
		return safeUnitService;
	}
	public void setSafeUnitService(XtglSafeUnitService safeUnitService) {
		this.safeUnitService = safeUnitService;
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
		String sql="select de.*,g.sim as sim," +
									" xuu.name as useUnitName,xuu.phone as useUnitPhone, " +
									" xmu.name as  maintenanceUnitName,xmu.phone as maintenanceUnitPhone," +
									" mu.name as  maintenanceUserName,mu.phone as maintenanceUserPhone," +
									" xpu.name as  propertyUnitName,xpu.phone as propertyUnitPhone," +
									" make.name as  makeUnitName,make.phone as makeUnitPhone," +
									" xu.name as  usersName,xu.phone as usersPhone " +
							" from dtjk_elevator de " +
							" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
							" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
							" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
							" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
							" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
							" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
							" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
							" where  1=1  and de.delflag!='1' " ;
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
					elevator.setPropertyUnitName(rs.getString("propertyUnitName"));
					elevator.setMakeUnitName(rs.getString("makeUnitName"));
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
		String time=df.format(new Date());
		if(entity.getStartTime().getTime()<=df.parse(time).getTime()&&df.parse(time).getTime()<=entity.getEndTime().getTime()){
			elevator.setSafeState("1");
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
		String company=request.getParameter("company");
		String beneficiary=request.getParameter("beneficiary");
		
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
				String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname,xsu.name as safeUnitName  " +
						" from Bxgl_Safe de " +			
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //使用单位
						" left join Xtgl_Safe_Unit xsu on xsu.id=de.safe_Unit_Id "+  //保险单位
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
				if(company!=null&&!company.equals("")){
					company=new String(company.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.company like '%"+company+"%'";
				}
				if(beneficiary!=null&&!beneficiary.equals("")){
					beneficiary=new String(beneficiary.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.beneficiary like '%"+beneficiary+"%'";
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
					useUnit.setNumber(rs.getString("numbers"));
					useUnit.setCompany(rs.getString("company"));
					useUnit.setSafeUnitName(rs.getString("safeUnitName"));
					String sql3="select count(*)  from Bxgl_Claim de where  1=1  and safe_Id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql3);
					useUnit.setNum(n);
					list.add(useUnit);
					
				}
				
				
				request.setAttribute("elevatorId", elevatorId);
				request.setAttribute("company", company);
				request.setAttribute("beneficiary", beneficiary);
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
			entity.setNumber(unit.getNumber());
			entity.setCompany(unit.getCompany());
			entity.setSafeUnitId(unit.getSafeUnitId());
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
		Long elevatorId=safeService.get(id).getElevatorId().getId();
		DtjkElevator entity =elevatorService.get(elevatorId);
		safeService.delete(id);
		

		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除电梯电梯保险记录，电梯注册号："+entity.getRegisterid(), "1");
		 return	new ActionForward("/safeAction.do?method=query&elevatorId="+elevatorId);
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
		Long elevatorId = null;
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				elevatorId  =safeService.get(Long.parseLong(arr[i])).getElevatorId().getId();
				safeService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除电梯电梯保险记录", "1");
			}
		}
		 return	new ActionForward("/safeAction.do?method=query&elevatorId="+elevatorId);
		
	}
	
	/**
	 * 保险统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  querySafe(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String makeUnitId=request.getParameter("makeUnitId");
		String makeUnitId1=request.getParameter("makeUnitId1");
		String useUnitId=request.getParameter("useUnitId");
		String useUnitId1=request.getParameter("useUnitId1");
		String propertyUnitId=request.getParameter("propertyUnitId");
		String propertyUnitId1=request.getParameter("propertyUnitId1");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		String maintenanceUnitId1=request.getParameter("maintenanceUnitId1");
		
				
				//查询服务订单
		String sql="select count(de.id)" +
							" from dtjk_elevator de " +
							" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
							" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
							" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
							" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
							" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
							" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
							" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
							" where  1=1  and de.delflag!='1' " ;
		String sql1="select count(c.id)" +
		" from Bxgl_Claim c " +			
		" left join Bxgl_Safe e on e.id=c.Safe_id "+  //保单记录信息
		" left join dtjk_elevator de on de.id=e.elevator_id "+  //电梯信息
		" left join Xtgl_Safe_Unit xsu on xsu.id=e.safe_Unit_Id "+  //保险单位
		" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
		" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
		" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
		" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
		" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
		" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
		" where  1=1  and de.delflag!='1' " ;
		String sql2="select max(count(c.id)) " +
		" from Bxgl_Claim c " +			
		" left join Bxgl_Safe e on e.id=c.Safe_id "+  //保单记录信息
		" left join dtjk_elevator de on de.id=e.elevator_id "+  //电梯信息
		" left join Xtgl_Safe_Unit xsu on xsu.id=e.safe_Unit_Id "+  //保险单位
		" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
		" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
		" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
		" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
		" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
		" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
		" where  1=1  and de.delflag!='1' " ;
		if(makeUnitId!=null&&!makeUnitId.equals("")){
			sql+=" and make.id = '"+makeUnitId+"'";
			sql1+=" and make.id = '"+makeUnitId+"'";
			sql2+=" and make.id = '"+makeUnitId+"'";
		}
		if(useUnitId!=null&&!useUnitId.equals("")){
			sql+=" and xuu.id = '"+useUnitId+"'";
			sql1+=" and xuu.id = '"+useUnitId+"'";
			sql2+=" and xuu.id = '"+useUnitId+"'";
		}
		if(propertyUnitId!=null&&!propertyUnitId.equals("")){
			sql+=" and xpu.id= '"+propertyUnitId+"'";
			sql1+=" and xpu.id= '"+propertyUnitId+"'";
			sql2+=" and xpu.id= '"+propertyUnitId+"'";
		}
		if(maintenanceUnitId!=null&&!maintenanceUnitId.equals("")){
			sql+=" and xmu.id= '"+maintenanceUnitId+"'";
			sql1+=" and xmu.id= '"+maintenanceUnitId+"'";
			sql2+=" and xmu.id= '"+maintenanceUnitId+"'";
		}
		
				DecimalFormat    df   = new DecimalFormat("#0.00");   
				Safe safe=new Safe();
				//电梯总数
				safe.setZong(DBEntity.getInstance().queryDataCount(sql));
				//未保电梯数
				safe.setWei(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State is null "));
				//在保电梯数
				safe.setZai(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State='1' "));
				//脱保电梯数
				safe.setTuo(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State='0'"));
				//未保率
				safe.setWeiRate( safe.getZong()!=0?df.format(((double)safe.getWei()/safe.getZong())):"0");
				//在保率
				safe.setZaiRate( safe.getZong()!=0?df.format(((double)safe.getZai()/safe.getZong())):"0");
				//脱保率
				safe.setTuoRate( safe.getZong()!=0?df.format(((double)safe.getTuo()/safe.getZong())):"0");
				//受理赔次数
				safe.setClaimNum(DBEntity.getInstance().queryDataCount(sql1));
				//理赔率
				safe.setClaimRate(safe.getZong()!=0?df.format(((double)safe.getClaimNum()/safe.getZai())):"0");
				//最高受理赔次数
				safe.setMostNum(DBEntity.getInstance().queryDataCount(sql2+" group by e.id"));
				if(makeUnitId1!=null&&!makeUnitId1.equals(""))
					makeUnitId1=new String(makeUnitId1.getBytes("ISO-8859-1"),"utf-8");
				if(useUnitId1!=null&&!useUnitId1.equals(""))
					useUnitId1=new String(useUnitId1.getBytes("ISO-8859-1"),"utf-8");
				if(propertyUnitId1!=null&&!propertyUnitId1.equals(""))
					propertyUnitId1=new String(propertyUnitId1.getBytes("ISO-8859-1"),"utf-8");
				if(maintenanceUnitId1!=null&&!maintenanceUnitId1.equals(""))
					maintenanceUnitId1=new String(maintenanceUnitId1.getBytes("ISO-8859-1"),"utf-8");
				request.setAttribute("safe", safe);
				request.setAttribute("makeUnitId", makeUnitId);
				request.setAttribute("makeUnitId1", makeUnitId1);
				request.setAttribute("useUnitId", useUnitId);
				request.setAttribute("useUnitId1",useUnitId1);
				request.setAttribute("propertyUnitId", propertyUnitId);
				request.setAttribute("propertyUnitId1", propertyUnitId1);
				request.setAttribute("maintenanceUnitId", maintenanceUnitId);
				request.setAttribute("maintenanceUnitId1", maintenanceUnitId1);
		
				return	new ActionForward("/jsp/Insurance/count/safeCount.jsp");
		}
	/**
	 * 导出 保险统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  exportSafe(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String makeUnitId=request.getParameter("makeUnitId");
		String useUnitId=request.getParameter("useUnitId");
		String propertyUnitId=request.getParameter("propertyUnitId");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		
				
				//查询服务订单
		String sql="select count(de.id)" +
							" from dtjk_elevator de " +
							" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
							" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
							" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
							" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
							" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
							" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
							" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
							" where  1=1  and de.delflag!='1' " ;
		String sql1="select count(c.id)" +
		" from Bxgl_Claim c " +			
		" left join Bxgl_Safe e on e.id=c.Safe_id "+  //保单记录信息
		" left join dtjk_elevator de on de.id=e.elevator_id "+  //电梯信息
		" left join Xtgl_Safe_Unit xsu on xsu.id=e.safe_Unit_Id "+  //保险单位
		" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
		" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
		" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
		" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
		" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
		" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
		" where  1=1  and de.delflag!='1' " ;
		String sql2="select max(count(c.id)) " +
		" from Bxgl_Claim c " +			
		" left join Bxgl_Safe e on e.id=c.Safe_id "+  //保单记录信息
		" left join dtjk_elevator de on de.id=e.elevator_id "+  //电梯信息
		" left join Xtgl_Safe_Unit xsu on xsu.id=e.safe_Unit_Id "+  //保险单位
		" left join Dtjk_gateway g on g.elevator_Id=de.registerid "+  //网关
		" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //使用单位
		" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //维保单位
		" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //维保人
		" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //物业单位
		" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //制造单位
		" left join Xtgl_Users xu on xu.id=de.userid"+  //区域用户
		" where  1=1  and de.delflag!='1' " ;
		if(makeUnitId!=null&&!makeUnitId.equals("")){
			sql+=" and make.id = '"+makeUnitId+"'";
			sql1+=" and make.id = '"+makeUnitId+"'";
			sql2+=" and make.id = '"+makeUnitId+"'";
		}
		if(useUnitId!=null&&!useUnitId.equals("")){
			sql+=" and xuu.id = '"+useUnitId+"'";
			sql1+=" and xuu.id = '"+useUnitId+"'";
			sql2+=" and xuu.id = '"+useUnitId+"'";
		}
		if(propertyUnitId!=null&&!propertyUnitId.equals("")){
			sql+=" and xpu.id= '"+propertyUnitId+"'";
			sql1+=" and xpu.id= '"+propertyUnitId+"'";
			sql2+=" and xpu.id= '"+propertyUnitId+"'";
		}
		if(maintenanceUnitId!=null&&!maintenanceUnitId.equals("")){
			sql+=" and xmu.id= '"+maintenanceUnitId+"'";
			sql1+=" and xmu.id= '"+maintenanceUnitId+"'";
			sql2+=" and xmu.id= '"+maintenanceUnitId+"'";
		}
		
				DecimalFormat    df   = new DecimalFormat("#0.00");   
				Safe safe=new Safe();
				//电梯总数
				safe.setZong(DBEntity.getInstance().queryDataCount(sql));
				//未保电梯数
				safe.setWei(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State is null "));
				//在保电梯数
				safe.setZai(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State='1' "));
				//脱保电梯数
				safe.setTuo(DBEntity.getInstance().queryDataCount(sql+" and de.safe_State='0'"));
				//未保率
				safe.setWeiRate( safe.getZong()!=0?df.format(((double)safe.getWei()/safe.getZong())):"0");
				//在保率
				safe.setZaiRate( safe.getZong()!=0?df.format(((double)safe.getZai()/safe.getZong())):"0");
				//脱保率
				safe.setTuoRate( safe.getZong()!=0?df.format(((double)safe.getTuo()/safe.getZong())):"0");
				//受理赔次数
				safe.setClaimNum(DBEntity.getInstance().queryDataCount(sql1));
				//理赔率
				safe.setClaimRate(safe.getZong()!=0?df.format(((double)safe.getClaimNum()/safe.getZai())):"0");
				//最高受理赔次数
				safe.setMostNum(DBEntity.getInstance().queryDataCount(sql2+" group by e.id"));
		List<Safe> list=new ArrayList<Safe>();
		list.add(safe);
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "保险统计" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			safeService.exportSafe(filePath, list);

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
	 * 保险公司统计
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  querySafeUnit (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
				List<SafeUnit> list=new ArrayList<SafeUnit>();
				String sql=" select xsu.name as name, " +
						"					(select count(de.id) from BXGL_SAFE s  left join dtjk_elevator de on de.id=s.elevator_id where s.safe_unit_id = xsu.id) as num, " +
						" 					(select count(c.id) from Bxgl_Claim c left join Bxgl_Safe s on s.id=c.Safe_id  where s.safe_unit_id = xsu.id) as claimNum " +
						"			 	from Xtgl_Safe_Unit xsu " +
						"				where 1 = 1";
				Connection conn=DBEntity.getInstance().getConnection();
				PreparedStatement sta = conn.prepareStatement(sql);
				ResultSet rs = sta.executeQuery();
				DecimalFormat    df   = new DecimalFormat("#0.00");   
				List<String> names=new ArrayList<String>();
				List<String> num=new ArrayList<String>();
				List<String> claimNum=new ArrayList<String>();
				List<String> claimRate=new ArrayList<String>();
				while(rs.next()){
					SafeUnit unit=new SafeUnit();
					unit.setName(rs.getString("name"));
					unit.setNum(rs.getInt("num"));
					unit.setClaimNum(rs.getInt("claimNum"));
					unit.setClaimRate(unit.getNum()!=0?df.format((double)unit.getClaimNum()/unit.getNum()):"0");
					list.add(unit);
					if(rs.getString("name")==null&&rs.getString("name").equals("")){
						names.add("\"\"");
					}else{
						names.add("\""+rs.getString("name")+"\"");
					}
					
					num.add(rs.getString("num"));
					claimNum.add(rs.getString("claimNum"));
					claimRate.add(unit.getNum()!=0?df.format((double)unit.getClaimNum()/unit.getNum()):"0");
				}
				request.setAttribute("names", names);
				request.setAttribute("claimNum", claimNum);
				request.setAttribute("claimRate", claimRate);
				request.setAttribute("num", num);
				request.setAttribute("list", list);
				return	new ActionForward("/jsp/Insurance/count/SafeUnitCount.jsp");
		}
	/**
	 * 导出 保险单位统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  exportSafeUnit(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		List<SafeUnit> list=new ArrayList<SafeUnit>();
		String sql=" select xsu.name as name, " +
				"					(select count(de.id) from BXGL_SAFE s  left join dtjk_elevator de on de.id=s.elevator_id where s.safe_unit_id = xsu.id) as num, " +
				" 					(select count(c.id) from Bxgl_Claim c left join Bxgl_Safe s on s.id=c.Safe_id  where s.safe_unit_id = xsu.id) as claimNum " +
				"			 	from Xtgl_Safe_Unit xsu " +
				"				where 1 = 1";
		Connection conn=DBEntity.getInstance().getConnection();
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		DecimalFormat    df   = new DecimalFormat("#0.00");   
		while(rs.next()){
			SafeUnit unit=new SafeUnit();
			unit.setName(rs.getString("name"));
			unit.setNum(rs.getInt("num"));
			unit.setClaimNum(rs.getInt("claimNum"));
			unit.setClaimRate(unit.getNum()!=0?df.format((double)unit.getClaimNum()/unit.getNum()):"0");
			list.add(unit);
		}
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "保险单位统计" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			safeService.exportSafeUnit(filePath, list);

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
}
