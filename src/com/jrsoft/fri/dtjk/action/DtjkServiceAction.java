package com.jrsoft.fri.dtjk.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkService;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkServiceService;
import com.jrsoft.fri.xtgl.from.Page;

public class DtjkServiceAction  extends DispatchAction {
	
	private DtjkServiceService serviceService;
	private DtjkElevatorService elevatorService;
	public DtjkServiceService getServiceService() {
		return serviceService;
	}
	public void setServiceService(DtjkServiceService serviceService) {
		this.serviceService = serviceService;
	}
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}
	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	/**
	 * 跳转 年检记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByAdd(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator list=elevatorService.get(Long.parseLong(elevatorId));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dtjk/service/addService.jsp");
	}
	
	
	
	/**
	 * 查询 电梯服务费记录列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String useUnitId1=request.getParameter("useUnitId1");
		String inspectionUnit=request.getParameter("inspectionUnit");
		
		String num=request.getParameter("num");   //当前页
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		if(useUnitId1!=null){
			useUnitId1=new String(useUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<DtjkService> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
						" from Dtjk_Service de " +			
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //电梯信息
						" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //使用单位
						" where  1=1 " ;
				
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				if(time!=null&&!time.equals("")){
					sql+=" and de.time = to_date('" + time+ "','yyyy-MM-dd')";
				}
				if(useUnitId!=null&&!useUnitId.equals("")){
					sql+=" and de.use_unit_id  ='"+useUnitId+"'";
				}
				if(inspectionUnit!=null&&!inspectionUnit.equals("")){
					sql+=" and de.inspection_unit  ='"+inspectionUnit+"'";
				}
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkService>();
				while(rs.next()){
					DtjkService useUnit=new DtjkService();
					useUnit.setId(rs.getLong("id"));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setUseUnitName(rs.getString("userunitname"));
					useUnit.setStartTime(df.parse(rs.getString("start_Time")));
					useUnit.setEndTime(df.parse(rs.getString("end_Time")));
					useUnit.setMoney(rs.getLong("money"));
					list.add(useUnit);
					
				}
				
				
				request.setAttribute("useUnitId", useUnitId);
				request.setAttribute("useUnitId1", useUnitId1);
				request.setAttribute("inspectionUnit", inspectionUnit);
				request.setAttribute("elevatorId", elevatorId);
				if(time!=null){
					request.setAttribute("time", df.parse(time));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/service/serviceList.jsp");
		}

}
