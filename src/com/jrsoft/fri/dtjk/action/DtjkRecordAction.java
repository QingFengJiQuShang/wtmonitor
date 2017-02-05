package com.jrsoft.fri.dtjk.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
import com.jrsoft.fri.dtjk.from.Control;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkGatewayService;
import com.jrsoft.fri.dtjk.service.DtjkRecordService;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;

public class DtjkRecordAction extends DispatchAction {
	
	private DtjkRecordService recordService;
	private DtjkElevatorService elevatorService;
	private DtjkGatewayService gatewayService;
	private XtszDictionaryService dictionaryService;
	public DtjkRecordService getRecordService() {
		return recordService;
	}

	public void setRecordService(DtjkRecordService recordService) {
		this.recordService = recordService;
	}

	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	public DtjkGatewayService getGatewayService() {
		return gatewayService;
	}

	public void setGatewayService(DtjkGatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}
	public XtszDictionaryService getDictionaryService() {
		return dictionaryService;
	}

	public void setDictionaryService(XtszDictionaryService dictionaryService) {
		this.dictionaryService = dictionaryService;
	}

	/**
	 * 查看 电梯监控 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByMonitor(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkElevator list=elevatorService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		String hql=" where foundTime= ( select max(foundTime) from DtjkRecord where elevatorId='"+list.getRegisterid()+"')  ";
		//String hql="select t.* form () where elevatorId='"+list.getRegisterid()+"' order by t.foundTime desc    ";
		List<DtjkRecord> record=recordService.query(hql);
		String hql2=" where  1=1 and elevatorId='"+list.getRegisterid()+"' " ;
		List<DtjkGateway> gateways=gatewayService.queryAll(hql2);
		if(gateways.size()>0){
			request.setAttribute("gateway", gateways.get(0));
		}
		if(record.size()>0){
			DtjkRecord records=record.get(0);
			request.setAttribute("records", records);
		}
		//重新刷新时间
		String hql1=" where flag='0'";
		List<XtszDictionary> dictionaries=dictionaryService.query(hql1);
		if(dictionaries.size()>0){
			request.setAttribute("dictionarie", dictionaries.get(0));
		}
		if(flag.equals("1")&&flag!=null)
			return	new ActionForward("/jsp/dtjk/monitor/left.jsp");
		else
			return	new ActionForward("/jsp/dtjk/monitor/monitorDetail.jsp");
		
	}
	
	/**
	 * 查看 多台电梯监控 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByControl(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		String flag=request.getParameter("flag");
		List<Control> controls=new ArrayList<Control>();
		//String hql="select t.* form () where elevatorId='"+list.getRegisterid()+"' order by t.foundTime desc    ";
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				Control control=new Control();
				DtjkElevator list=elevatorService.get(Long.parseLong(arr[i]));
				control.setElevator(list);
				String hql=" where foundTime= ( select max(foundTime) from DtjkRecord where elevatorId='"+list.getRegisterid()+"')  ";
				List<DtjkRecord> record=recordService.query(hql);
				String hql2=" where  1=1 and elevatorId='"+list.getRegisterid()+"' " ;
				List<DtjkGateway> gateways=gatewayService.queryAll(hql2);
				if(gateways.size()>0){
					control.setGateway( gateways.get(0));
				}
				if(record.size()>0){
					control.setRecord(record.get(0));
				}
				controls.add(control);
			}
		}
		//重新刷新时间
		String hql1=" where flag='0'";
		List<XtszDictionary> dictionaries=dictionaryService.query(hql1);
		if(dictionaries.size()>0){
			request.setAttribute("dictionarie", dictionaries.get(0));
		}
		request.setAttribute("list", controls);
		request.setAttribute("ids", ids);
			return	new ActionForward("/jsp/dtjk/monitor/more.jsp");
		
	}
	
	/**
	 * 查询 电梯上报记录列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		
		String elevatorId=request.getParameter("elevatorId");
		String direction=request.getParameter("direction");
		String people=request.getParameter("people");
		String door=request.getParameter("door");
		String num=request.getParameter("num");   //当前页
		
		 if(direction!=null){
			 direction=new String(direction.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(people!=null){
			 people=new String(people.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(door!=null){
			 door=new String(door.getBytes("iso-8859-1"),"utf-8");
		 }

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<DtjkRecord> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from dtjk_record de  where  1=1 " ;
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and elevator_Id = '"+elevatorId+"'";
				}
				if(direction!=null&&!direction.equals("")){
					sql+=" and direction = '"+direction+"'";
				}
				if(people!=null&&!people.equals("")){
					sql+=" and people = '"+people+"'";
				}
				if(door!=null&&!door.equals("")){
					sql+=" and door = '"+door+"'";
				}
				sql+=" order by de.found_time desc  ";	
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				String sql2="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				PreparedStatement sta = conn.prepareStatement(sql2);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkRecord>();
				while(rs.next()){
					DtjkRecord elevator=new DtjkRecord();
					elevator.setId(rs.getLong("id"));
					elevator.setSerialNumber(rs.getString("serial_Number"));
					elevator.setElevatorId(rs.getString("elevator_Id"));
					elevator.setDirection(rs.getString("direction"));
					elevator.setSpeed(rs.getString("speed"));
					elevator.setFloor(rs.getString("floor"));
					elevator.setGatewayDate(rs.getString("gateway_Date"));
					elevator.setGatewayTime(rs.getString("gateway_Time"));
					elevator.setPeople(rs.getString("people"));
					elevator.setDoor(rs.getString("door"));
					elevator.setHeartbeat(rs.getString("heartbeat"));
					elevator.setMaintenanceUserId(rs.getString("maintenance_User_Id"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					list.add(elevator);
					
				}
				
				request.setAttribute("elevatorId", elevatorId);
				request.setAttribute("direction",direction );
				request.setAttribute("people", people);
				request.setAttribute("door", door);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/playback/playbackList.jsp");
		}
	
	/**
	 * 查看 电梯详细上报记录
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		DtjkRecord list=recordService.get(Long.parseLong(id));
		String hql=" where registerid='"+list.getElevatorId()+"' ";
		List<DtjkElevator> elevators=elevatorService.query(hql);
		if(elevators.size()>0){
			request.setAttribute("list", elevators.get(0));
		}
			
		request.setAttribute("records", list);
		return	new ActionForward("/jsp/dtjk/playback/monitorDetail.jsp");
		
	}

}
