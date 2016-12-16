package com.jrsoft.fri.tjfx.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.entity.GzclRescue;
import com.jrsoft.fri.gzcl.service.GzclFaultService;
import com.jrsoft.fri.gzcl.service.GzclRescueService;
import com.jrsoft.fri.tjfx.from.BrandCount;
import com.jrsoft.fri.tjfx.from.FaultCount;
import com.jrsoft.fri.tjfx.from.RescueCount;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglRescueUnitService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;

public class CountAction  extends DispatchAction{
	private DtjkElevatorService elevatorService;
	private GzclFaultService faultService;
	private XtglMaintenanceUnitService maintenanceUnitService;
	private XtglMaintenanceUsersService maintenanceUsersService;
	private XtglRescueUnitService rescueUnitService;
	private XtglUsersService usersService;
	private XtglUseUnitService useUnitService;
	private GzclRescueService gzclRescueService;

	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}
	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	public GzclFaultService getFaultService() {
		return faultService;
	}
	public void setFaultService(GzclFaultService faultService) {
		this.faultService = faultService;
	}
	public XtglMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}
	public void setMaintenanceUnitService(
			XtglMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}
	public XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}
	public void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		this.maintenanceUsersService = maintenanceUsersService;
	}
	public XtglRescueUnitService getRescueUnitService() {
		return rescueUnitService;
	}
	public void setRescueUnitService(XtglRescueUnitService rescueUnitService) {
		this.rescueUnitService = rescueUnitService;
	}
	public XtglUsersService getUsersService() {
		return usersService;
	}
	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}
	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}
	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}
	public GzclRescueService getGzclRescueService() {
		return gzclRescueService;
	}
	public void setGzclRescueService(GzclRescueService gzclRescueService) {
		this.gzclRescueService = gzclRescueService;
	}
	
	
	/**
	 * 故障统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  faultCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		DecimalFormat    df   = new DecimalFormat("0.00");   
		Connection conn=DBEntity.getInstance().getConnection();
		String sql="select count(de.id) from dtjk_elevator de where de.delflag='0'" ;
		int zong=DBEntity.getInstance().queryDataCount(sql); 		//电梯总数
		int gz=0;																								//故障总数
		
		 sql="select distinct (g.fault_Type) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
		 if(begintime!=null&&!begintime.equals("")){
				sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(endtime!=null&&!endtime.equals("")){
				sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<FaultCount> counts=new ArrayList<FaultCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			JSONObject json = new JSONObject(); 
			FaultCount count=new FaultCount();
			
			count.setFaultType(rs.getString("fault_Type"));		//故障类型
			String sql1="select g.* from  gzcl_fault g    left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0' and g.fault_Type='"+rs.getString("fault_Type")+"' ";
			if(begintime!=null&&!begintime.equals("")){
				sql1+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(endtime!=null&&!endtime.equals("")){
				sql1+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			int faultNum=DBEntity.getInstance().queryCount(sql1);
			count.setFaultNum(faultNum);		//故障次数
			gz+=faultNum;
			count.setElevatorNum(zong);		//电梯次数
			float num= (float)faultNum/zong;
			count.setIncidence(df.format(num*100));
			title.add(count.getFaultType() );
			json.put("value",count.getIncidence() );
			json.put("name",count.getFaultType() );
			counts.add(count);
			rows.add(json);
		}
		
		request.setAttribute("rows",rows.toString() );
		request.setAttribute("title",title.toString() );
		float num= (float)gz/zong;
		String gzl=df.format(num*100);														//故障率
		request.setAttribute("zong",zong );
		request.setAttribute("gz",gz );
		request.setAttribute("gzl",gzl );
		request.setAttribute("counts",counts );
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
	    return	new ActionForward("/jsp/count/fault.jsp");
	}
	
	
	
	/**
	 * 救援统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  rescueCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String unit=request.getParameter("unit");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.0");   
		String hql=" where 1=1 ";
		if(unit!=null&&!unit.equals("")){
			unit=new String(unit.getBytes("ISO-8859-1"),"utf-8");
			hql+=" and name like '%"+unit+"%' ";
		}
		List<XtglRescueUnit> list=rescueUnitService.query(hql);
		List<RescueCount> counts=new ArrayList<RescueCount>();
		JSONArray rows =JSONArray.fromObject("[{'value':0,'name':'0-5'},{'value':0,'name':'5-10'},{'value':0,'name':'10-15'},{'value':0,'name':'15-20'},{'value':0,'name':'20-25'},{'value':0,'name':'25-30'},{'value':0,'name':'30分钟以上'}]");				//详细数据
		JSONArray rows1 =JSONArray.fromObject("[{'value':0,'name':'0-5'},{'value':0,'name':'5-10'},{'value':0,'name':'10-15'},{'value':0,'name':'15-20'},{'value':0,'name':'20-25'},{'value':0,'name':'25-30'},{'value':0,'name':'30分钟以上'}]");				//详细数据
		JSONArray title = JSONArray.fromObject("['0-5','5-10','10-15','15-20','20-25','25-30','30分钟以上']");			//饼状图 标题
		
		for(int i=0;i<list.size();i++){
			RescueCount count=new RescueCount();
			count.setName(list.get(i).getName());
			//查询 救援到达时间和救援完成时间
			String hql1=" where rescueUnitId = '"+list.get(i).getId()+"'";
			if(begintime!=null&&!begintime.equals("")){
				hql1+=" and faultId.happenTime  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			if(endtime!=null&&!endtime.equals("")){
				hql1+=" and faultId.happenTime  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
			}
			List<GzclRescue> rescues=gzclRescueService.query(hql1);
			
			double  arriveTime=0;
			double successTime=0;
			
			for(GzclRescue g:rescues){
				if(g.getFaultId().getArriveTime()!=null&&g.getFaultId().getHappenTime()!=null&&g.getFaultId().getSuccessTime()!=null){
					long arrive=(g.getFaultId().getArriveTime().getTime()-g.getFaultId().getHappenTime().getTime());
					arriveTime+= arrive /(1000*60);  		 //  救援到达时间  分钟
					long success=(g.getFaultId().getSuccessTime().getTime()-g.getFaultId().getArriveTime().getTime());
					successTime+= success /(1000*60);  		 //  救援成功时间  分钟
					judge(rows,arrive /(1000*60));
					judge(rows1,success /(1000*60));
				}
			}
			if(arriveTime!=0&&rescues.size()!=0){
				count.setArriveTime(df.format(arriveTime/rescues.size()));
			}else{
				count.setArriveTime("0");
			}
			if(successTime!=0&&rescues.size()!=0){
				count.setSuccessTime(df.format(successTime/rescues.size()));
			}else{
				count.setSuccessTime("0");
			}
			count.setNum(rescues.size());
			counts.add(count);
		}
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("unit", unit);
		request.setAttribute("rows", rows);
		request.setAttribute("rows1", rows1);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/rescue.jsp");
	}
	
	/**
	 * 救援达到或者救援成功次数判断
	 * @throws Exception 
	 */
	public void judge(JSONArray rows ,double arrive) throws Exception{
		if(arrive<5){
			JSONObject json1 =(JSONObject) rows.get(0);
			json1.put("value", json1.getInt("value")+1);
		}else if(arrive<=10){
				JSONObject json1 =(JSONObject) rows.get(1);
				json1.put("value", json1.getInt("value")+1);
		}else if(arrive<=15){
			JSONObject json1 =(JSONObject) rows.get(2);
			json1.put("value", json1.getInt("value")+1);
		}else if(arrive<=20){
			JSONObject json1 =(JSONObject) rows.get(3);
			json1.put("value", json1.getInt("value")+1);
		}else if(arrive<=25){
			JSONObject json1 =(JSONObject) rows.get(4);
			System.out.println(json1.toString());
			json1.put("value", json1.getInt("value")+1);
		}else if(arrive<=30){
			JSONObject json1 =(JSONObject) rows.get(5);
			json1.put("value", json1.getInt("value")+1);
		}else{
			JSONObject json1 =(JSONObject) rows.get(6);
			json1.put("value", json1.getInt("value")+1);	
		}
	}
	

	/**
	 * 品牌统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  brandCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");	
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.00");   
		
		Connection conn=DBEntity.getInstance().getConnection();																	//故障总数
		
		String sql="select distinct (de.brand)  from dtjk_elevator  de  where de.delflag='0'  " ;
		 
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<BrandCount> counts=new ArrayList<BrandCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			BrandCount count=new BrandCount();
			 count.setBrand(rs.getString("brand"));						//电梯品牌
			 sql="select count(de.id) from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("brand")!=null){
				 sql+= "and de.brand='"+rs.getString("brand")+"' " ;
			 }else{
				 sql+= "and de.brand is null " ;
			 }
			 int num=DBEntity.getInstance().queryDataCount(sql); 	
			 count.setNum(num);						//品牌电梯数量
			 sql="select count(g.id) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
			 if(rs.getString("brand")!=null){
				 sql+= "and de.brand='"+rs.getString("brand")+"' " ;
			 }else{
				 sql+= "and de.brand is null " ;
			 }
			 if(begintime!=null&&!begintime.equals("")){
					sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
			 int faultNum=DBEntity.getInstance().queryDataCount(sql);
			 count.setFaultNum(faultNum);						//故障次数
			 float l= (float)faultNum/num;
			 String incidence=df.format(l*100);														//故障率
			 count.setIncidence(incidence);				//故障发生率
			 String begin="";
			 String end="";
			 	if(begintime!=null&&!begintime.equals("")){
			 		begin="to_date('" +sdf.format(sdf.parse(begintime)) + "','yyyy-MM-dd')";
				}else{
					begin="de.install_time";
				}
				if(endtime!=null&&!endtime.equals("")){
					end=sdf.format(sdf.parse(endtime)) ;
				}else{
					end=sdf.format(new Date()) ;
				}
			 sql="select sum(to_date('" +end+ "','yyyy-MM-dd')-"+begin+") from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("brand")!=null){
				 sql+= "and de.brand='"+rs.getString("brand")+"' " ;
			 }else{
				 sql+= "and de.brand is null " ;
			 }
			 
			 int time=DBEntity.getInstance().queryDataCount(sql); 	
			 if(time!=0&&num!=0&&faultNum!=0){
				 count.setTime(df.format((time*num)/faultNum));
				 rows.add((time*num)/faultNum);
			 }else{
				 count.setTime("0");
				 rows.add(0);
			 }
			 
			 counts.add(count);
			 title.add(count.getBrand()==null?"":count.getBrand());
		}
		
		
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("rows", rows);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/brand.jsp");
	}
	

	/**
	 * 维保单位统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  maintenanceUnitCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");	
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.00");   
		
		Connection conn=DBEntity.getInstance().getConnection();																	//故障总数
		
		String sql="select distinct de.maintenance_Unit_Id,x.name  from dtjk_elevator  de   left join Xtgl_Maintenance_Unit x on x.id=de.maintenance_Unit_Id  where de.delflag='0'  " ;
		 
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<BrandCount> counts=new ArrayList<BrandCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			BrandCount count=new BrandCount();
			 count.setBrand(rs.getString("name"));						//电梯品牌
			 sql="select count(de.id) from dtjk_elevator de  where de.delflag='0' ";
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 int num=DBEntity.getInstance().queryDataCount(sql); 	
			 count.setNum(num);						//品牌电梯数量
			 sql="select count(g.id) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 if(begintime!=null&&!begintime.equals("")){
					sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
			 int faultNum=DBEntity.getInstance().queryDataCount(sql);
			 count.setFaultNum(faultNum);						//故障次数
			 float l= (float)faultNum/num;
			 String incidence=df.format(l*100);														//故障率
			 count.setIncidence(incidence);				//故障发生率
			 String begin="";
			 String end="";
			 	if(begintime!=null&&!begintime.equals("")){
			 		begin="to_date('" +sdf.format(sdf.parse(begintime)) + "','yyyy-MM-dd')";
				}else{
					begin="de.install_time";
				}
				if(endtime!=null&&!endtime.equals("")){
					end=sdf.format(sdf.parse(endtime)) ;
				}else{
					end=sdf.format(new Date()) ;
				}
			 sql="select sum(to_date('" +end+ "','yyyy-MM-dd')-"+begin+") from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("maintenance_Unit_Id")!=null){
				 sql+= "and de.maintenance_Unit_Id='"+rs.getString("maintenance_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.maintenance_Unit_Id is null " ;
			 }
			 
			 int time=DBEntity.getInstance().queryDataCount(sql); 	
			 if(time!=0&&num!=0&&faultNum!=0){
				 count.setTime(df.format((time*num)/faultNum));
				 rows.add((time*num)/faultNum);
			 }else{
				 count.setTime("0");
				 rows.add(0);
			 }
			 
			 counts.add(count);
			 title.add(count.getBrand()==null?"":count.getBrand());
		}
		
		
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("rows", rows);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/Maintenance.jsp");
	}
	

	/**
	 * 使用单位统计
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  useUnitCount(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String begintime=request.getParameter("begintime");	
		String endtime=request.getParameter("endtime");
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		
		DecimalFormat    df   = new DecimalFormat("0.00");   
		
		Connection conn=DBEntity.getInstance().getConnection();																	//故障总数
		
		String sql="select distinct de.use_Unit_Id,x.name  from dtjk_elevator  de  left join Xtgl_Use_Unit x on x.id=de.use_unit_id   where de.delflag='0'  " ;
		 
		PreparedStatement sta = conn.prepareStatement(sql);
		ResultSet rs = sta.executeQuery();
		List<BrandCount> counts=new ArrayList<BrandCount>();
		JSONArray rows = new JSONArray();				//详细数据
		JSONArray title = new JSONArray();				//饼状图 标题
		while(rs.next()){
			BrandCount count=new BrandCount();
			 count.setBrand(rs.getString("name"));						//电梯品牌
			 sql="select count(de.id) from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 int num=DBEntity.getInstance().queryDataCount(sql); 	
			 count.setNum(num);						//品牌电梯数量
			 sql="select count(g.id) from gzcl_fault g   left join dtjk_elevator de on de.id=g.elevator_Id  where de.delflag='0'  " ;
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 if(begintime!=null&&!begintime.equals("")){
					sql+=" and g.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and g.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
			 int faultNum=DBEntity.getInstance().queryDataCount(sql);
			 count.setFaultNum(faultNum);						//故障次数
			 float l= (float)faultNum/num;
			 String incidence=df.format(l*100);														//故障率
			 count.setIncidence(incidence);				//故障发生率
			 String begin="";
			 String end="";
			 	if(begintime!=null&&!begintime.equals("")){
			 		begin="to_date('" +sdf.format(sdf.parse(begintime)) + "','yyyy-MM-dd')";
				}else{
					begin="de.install_time";
				}
				if(endtime!=null&&!endtime.equals("")){
					end=sdf.format(sdf.parse(endtime)) ;
				}else{
					end=sdf.format(new Date()) ;
				}
			 sql="select sum(to_date('" +end+ "','yyyy-MM-dd')-"+begin+") from dtjk_elevator de where de.delflag='0' ";
			 if(rs.getString("use_Unit_Id")!=null){
				 sql+= "and de.use_Unit_Id='"+rs.getString("use_Unit_Id")+"' " ;
			 }else{
				 sql+= "and de.use_Unit_Id is null " ;
			 }
			 
			 int time=DBEntity.getInstance().queryDataCount(sql); 	
			 if(time!=0&&num!=0&&faultNum!=0){
				 count.setTime(df.format((time*num)/faultNum));
				 rows.add((time*num)/faultNum);
			 }else{
				 count.setTime("0");
				 rows.add(0);
			 }
			 
			 counts.add(count);
			 title.add(count.getBrand()==null?"":count.getBrand());
		}
		
		
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		request.setAttribute("rows", rows);
		request.setAttribute("title", title);
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/useNuit.jsp");
	}

}
