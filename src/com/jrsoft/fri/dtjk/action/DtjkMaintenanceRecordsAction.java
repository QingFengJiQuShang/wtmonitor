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
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkMaintenanceRecordsService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.action.Log;

public class DtjkMaintenanceRecordsAction extends DispatchAction {
	
	private DtjkMaintenanceRecordsService recordsService;
	private DtjkElevatorService elevatorService;
	public DtjkMaintenanceRecordsService getRecordsService() {
		return recordsService;
	}

	public void setRecordsService(DtjkMaintenanceRecordsService recordsService) {
		this.recordsService = recordsService;
	}
	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}

	/**
	 * �༭ �鿴 ά����¼
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
		return	new ActionForward("/jsp/dtjk/maintenanceRecords/addMaintenanceRecords.jsp");
	}
	
	/**
	 * ���� ά����¼��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkMaintenanceRecords elevator =DtjkFrom.getMaintenanceRecords();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		elevator.setTime(df.parse(time));
		recordsService.save(elevator);
		
		//�޸ĵ��ݵ�ά��״̬��ά��ʱ��
		DtjkElevator entity =elevatorService.get(elevator.getElevatorId().getId());
		entity.setMaintenanceTime(new Date());
		entity.setMaintenanceState("����");
		elevatorService.update(entity);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "��ӵ���ά����¼������ע��ţ�"+elevator.getRegisterid(), "1");
		
	    return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	/**
	 * ��ѯ ά����¼�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String useUnitId1=request.getParameter("useUnitId1");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		String maintenanceUnitId1=request.getParameter("maintenanceUnitId1");
		
		String num=request.getParameter("num");   //��ǰҳ
		
		if(useUnitId1!=null){
			useUnitId1=new String(useUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(maintenanceUnitId1!=null){
			 maintenanceUnitId1=new String(maintenanceUnitId1.getBytes("iso-8859-1"),"utf-8");
		 }
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkMaintenanceRecords> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as userunitname,xmu.name unitname,mu.name as username,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place  " +
						" from dtjk_maintenance_records de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.user_id"+  //ά����Ա
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
						"where  1=1 " ;
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				if(time!=null&&!time.equals("")){
					sql+=" and de.time = to_date('" + time+ "','yyyy-MM-dd')";
				}
				if(useUnitId!=null&&!useUnitId.equals("")){
					sql+=" and de.use_unit_id  ='"+useUnitId+"'";
				}
				if(maintenanceUnitId!=null&&!maintenanceUnitId.equals("")){
					sql+=" and de.unit_id  ='"+maintenanceUnitId+"'";
				}
				sql+=" order by time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkMaintenanceRecords>();
				while(rs.next()){
					DtjkMaintenanceRecords useUnit=new DtjkMaintenanceRecords();
					useUnit.setId(rs.getLong("id"));
					useUnit.setUseUnitName(rs.getString("userunitname"));
					useUnit.setUserName(rs.getString("username"));
					useUnit.setUnitName(rs.getString("unitname"));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setTime(df.parse(rs.getString("time")));
					useUnit.setContent(rs.getString("content"));
					list.add(useUnit);
					
				}
				request.setAttribute("elevatorId", elevatorId);
				if(time!=null){
					request.setAttribute("time", df.parse(time));
				}
				
				request.setAttribute("useUnitId", useUnitId);
				request.setAttribute("useUnitId1", useUnitId1);
				request.setAttribute("maintenanceUnitId", maintenanceUnitId);
				request.setAttribute("maintenanceUnitId1", maintenanceUnitId1);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/maintenanceRecords/maintenanceRecordsList.jsp");
		}
	
	/**
	 * �༭ �鿴 ά����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkMaintenanceRecords list=recordsService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/maintenanceRecords/updateMaintenanceRecords.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/maintenanceRecords/detailMaintenanceRecords.jsp");
		}
	}
	
	/**
	 * �޸�ά����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkMaintenanceRecords elevator =DtjkFrom.getMaintenanceRecords();
		String time=request.getParameter("time");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		elevator.setTime(df.parse(time));
		
		recordsService.update(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ĵ���ά����¼������ע��ţ�"+elevator.getRegisterid(), "1");
		return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	
	
	
	
	/**
	 * ɾ��ά����¼
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		DtjkElevator entity =elevatorService.get(id);
		recordsService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ������ά����¼������ע��ţ�"+entity.getRegisterid(), "1");
		 return	new ActionForward("/recordsAction.do?method=query&elevatorId="+elevatorId);
	}
	/**
	 * ���� ɾ�� ά����¼
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
				DtjkElevator entity =elevatorService.get(Long.parseLong(arr[i]));
				recordsService.delete(Long.parseLong(arr[i]));
				
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ������ά����¼������ע��ţ�"+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/recordsAction.do?method=query");
		
	}
	
	/**
	 * ���� ά����¼��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String elevatorId=request.getParameter("elevatorId");
		String time=request.getParameter("time");
		String useUnitId=request.getParameter("useUnitId");
		String maintenanceUnitId=request.getParameter("maintenanceUnitId");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//�������ڸ�ʽ	

		DtjkMaintenanceRecords records=new DtjkMaintenanceRecords();
		
		 XtglMaintenanceUnit unit=new XtglMaintenanceUnit();			//ά����λid
		 XtglUseUnit useUnit=new XtglUseUnit();			//ʹ�õ�λid
		 DtjkElevator elevator=new DtjkElevator();//ά������Id
		if(useUnitId!=null)
			useUnit.setId(Long.parseLong(useUnitId));
		 if(maintenanceUnitId!=null)
			 unit.setId(Long.parseLong(maintenanceUnitId));
		 if(elevatorId!=null)
			 elevator.setId(Long.parseLong(elevatorId));
		 if(time!=null)
			 records.setTime(df.parse(time));
		 records.setUnitId(unit);
		 records.setUseUnitId(useUnit);
		 records.setElevatorId(elevator);
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "ά����¼��Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			recordsService.export(filePath, records);

			// ����excel
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
				System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
			} else {
				System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}


}
