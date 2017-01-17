package com.jrsoft.fri.dtjk.action;

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
import com.jrsoft.fri.dtjk.entity.DtjkService;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkServiceService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.action.Log;

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
	 * ��ת ����¼
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
	 * ���� ����¼��Ϣ
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
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkService entity =DtjkFrom.getService();
		entity.setStartTime(df.parse(startTime));
		entity.setEndTime(df.parse(endTime));
		serviceService.save(entity);
		//�޸ĵ��ݵ����״̬�����ʱ��
		
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "��ӵ��ݷ���Ѽ�¼������ע��ţ�"+entity.getRegisterid(), "1");
		
	    return	new ActionForward("/serviceAction.do?method=query&elevatorId="+entity.getElevatorId().getId());
	}
	/**
	 * ���� ��ӵ��ݷ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  batchEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		String ids=request.getParameter("ids");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkService entity =DtjkFrom.getService();
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				DtjkService service=new DtjkService();
				DtjkElevator elevator =elevatorService.get(Long.parseLong(arr[i]));
				service.setStartTime(df.parse(startTime));
				service.setEndTime(df.parse(endTime));
				service.setElevatorId(elevator);
				service.setMoney(entity.getMoney());
				serviceService.save(service);
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "��ӵ��ݷ���Ѽ�¼������ע��ţ�"+entity.getRegisterid(), "1");
			}
		}
	    return	new ActionForward("/elevatorAction.do?method=queryManage");
	}
	/**
	 * ��ѯ ���ݷ���Ѽ�¼�б�
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
		
		String num=request.getParameter("num");   //��ǰҳ
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkService> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,e.install_place as place, e.registerid as registerid,e.distinguishid as distinguishid, xuu.name as userunitname  " +
						" from Dtjk_Service de " +			
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
						" left join xtgl_use_unit xuu on xuu.id=e.use_unit_id "+  //ʹ�õ�λ
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
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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
					useUnit.setType(rs.getString("type"));
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
		
		
		 return	new ActionForward("/jsp/dtjk/service/serviceList.jsp");
		}
	

	/**
	 * �༭ �鿴 ����Ѽ�¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkService list=serviceService.get(Long.parseLong(id));
		System.out.println(list.getElevatorId().getUseUnitId().getName());
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/service/updateService.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/service/dateilService.jsp");

		}
	}
	
	/**
	 * �޸ķ���Ѽ�¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String startTime=request.getParameter("startTime");
		String endTime=request.getParameter("endTime");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkService unit =DtjkFrom.getService();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkService entity=serviceService.get(unit.getId());
		if(entity!=null){
			entity.setMoney(unit.getMoney());
			entity.setType(unit.getType());
			entity.setElevatorId(unit.getElevatorId());
			entity.setStartTime(df.parse(startTime));
			entity.setEndTime(df.parse(endTime));
			serviceService.update(entity);
			DtjkElevator elevator =elevatorService.get(entity.getElevatorId().getId());

			//���� ������־
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			Log log=new Log();
	        log.addLog(user.getName(), "�޸ĵ��ݷ���Ѽ�¼������ע��ţ�"+elevator.getRegisterid(), "1");

		}	
		return	new ActionForward("/serviceAction.do?method=query&elevatorId="+entity.getElevatorId().getId());

	}
	
	
	
	/**
	 * ɾ������¼
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
		serviceService.delete(id);
		

		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ�����ݷ���Ѽ�¼������ע��ţ�"+entity.getRegisterid(), "1");
		 return	new ActionForward("/serviceAction.do?method=query&elevatorId="+elevatorId);
	}
	/**
	 * ���� ɾ�� ����¼
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
				serviceService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ�����ݷ���Ѽ�¼������ע��ţ�"+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/serviceAction.do?method=query&elevatorId="+elevatorId);
		
	}
}
