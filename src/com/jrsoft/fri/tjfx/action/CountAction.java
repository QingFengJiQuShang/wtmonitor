package com.jrsoft.fri.tjfx.action;

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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		DecimalFormat    df   = new DecimalFormat("0.0");   
		String hql=" where 1=1 ";
		List<XtglRescueUnit> list=rescueUnitService.query(hql);
		List<RescueCount> counts=new ArrayList<RescueCount>();
		
		for(int i=0;i<list.size();i++){
			RescueCount count=new RescueCount();
			count.setName(list.get(i).getName());
			//查询 救援到达时间和救援完成时间
			String hql1=" where rescueUnitId = '"+list.get(i).getId()+"'";
			List<GzclRescue> rescues=gzclRescueService.query(hql1);
			
			double  arriveTime=0;
			double successTime=0;
			
			for(GzclRescue g:rescues){
				if(g.getFaultId().getArriveTime()!=null&&g.getFaultId().getHappenTime()!=null&&g.getFaultId().getSuccessTime()!=null){
					long arrive=(g.getFaultId().getArriveTime().getTime()-g.getFaultId().getHappenTime().getTime());
					arriveTime+= arrive /(1000*60);  		 //  救援到达时间  分钟
					long success=(g.getFaultId().getSuccessTime().getTime()-g.getFaultId().getArriveTime().getTime());
					successTime+= success /(1000*60);  		 //  救援成功时间  分钟
				}
			}
			count.setArriveTime(df.format(arriveTime/rescues.size()));
			count.setSuccessTime(df.format(successTime/rescues.size()));
			count.setNum(rescues.size());
			counts.add(count);
		}
		if(endtime!=null&&!endtime.equals("")){
			request.setAttribute("endtime", sdf.parse( endtime));
		}
		if(begintime!=null&&!begintime.equals("")){
			request.setAttribute("begintime", sdf.parse( begintime));
		}
		
		request.setAttribute("counts", counts);
	    return	new ActionForward("/jsp/count/rescue.jsp");
	}

}
