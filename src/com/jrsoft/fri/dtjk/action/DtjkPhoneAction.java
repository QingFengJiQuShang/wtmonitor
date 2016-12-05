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
import smart.sys.platform.springUtils.SpringBeanUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkPhoneService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;
import com.jrsoft.fri.xtsz.action.Log;

public class DtjkPhoneAction extends DispatchAction{
	private DtjkPhoneService phoneService;
	private DtjkElevatorService elevatorService;
	
	private XtglMaintenanceUnitService maintenanceUnitService;
	private XtglUseUnitService useUnitService;
	private XtglUsersService usersService;
	
	public DtjkPhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(DtjkPhoneService phoneService) {
		this.phoneService = phoneService;
	}
	
	public DtjkElevatorService getElevatorService() {
		return elevatorService;
	}

	public void setElevatorService(DtjkElevatorService elevatorService) {
		this.elevatorService = elevatorService;
	}
	
	public XtglMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}

	public void setMaintenanceUnitService(
			XtglMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}

	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}

	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}

	public XtglUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
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
		DtjkElevator elevator=elevatorService.get(Long.parseLong(elevatorId));
		String hql=" where id='"+elevator.getMaintenanceUnitId().getId()+"'";
		List<XtglMaintenanceUnit> maintenanceUnits=maintenanceUnitService.query(hql);
		
		String hql1=" where id='"+elevator.getUseUnitId().getId()+"'";
		List<XtglUseUnit> useUnits=useUnitService.query(hql1);
		
		String hql2=" where id='"+elevator.getUseUnitId().getId()+"'";
		List<XtglUsers> users=usersService.query(hql2);
		
		List<String> list=new ArrayList<String>();
		for(XtglMaintenanceUnit maintenanceUnit: maintenanceUnits){
			list.add(maintenanceUnit.getPhone());
		}
		for(XtglUseUnit unit: useUnits){
			list.add(unit.getPhone());
		}
		for(XtglUsers user: users){
			list.add(user.getPhone());
		}
		request.setAttribute("list", list);
		request.setAttribute("elevatorId", elevatorId);
		
		return	new ActionForward("/jsp/dtjk/phone/addPhone.jsp");
	}
	/**
	 * ���� ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkPhone elevator =DtjkFrom.getPh();
		phoneService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "��ӵ��ݰ��������绰���룺"+elevator.getPhone(), "1");
		
	    return	new ActionForward("/phoneAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	/**
	 * ��ѯ ���ݰ������б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String elevatorId=request.getParameter("elevatorId");
		
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
		
		List<DtjkPhone> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.* ,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place " +
						" from dtjk_phone de " +
						" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
						" where  1=1 " ;
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_Id = '"+elevatorId+"'";
				}
				
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<DtjkPhone>();
				while(rs.next()){
					DtjkPhone elevators=new DtjkPhone();
					elevators.setId(rs.getLong("id"));
					elevators.setPhone(rs.getString("phone"));
					list.add(elevators);
					
				}

				request.setAttribute("elevatorId", elevatorId);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/phone/phoneList.jsp");
		}
	
	/**
	 * �༭ �鿴 ���� 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		DtjkPhone list=phoneService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			DtjkElevator elevator=elevatorService.get(list.getElevatorId().getId());
			String hql=" where id='"+list.getElevatorId().getMaintenanceUnitId().getId()+"'";
			List<XtglMaintenanceUnit> maintenanceUnits=maintenanceUnitService.query(hql);
			
			String hql1=" where id='"+list.getElevatorId().getUseUnitId().getId()+"'";
			List<XtglUseUnit> useUnits=useUnitService.query(hql1);
			
			String hql2=" where id='"+list.getElevatorId().getUserid().getId()+"'";
			List<XtglUsers> users=usersService.query(hql2);
			
			List<String> lists=new ArrayList<String>();
			for(XtglMaintenanceUnit maintenanceUnit: maintenanceUnits){
				lists.add(maintenanceUnit.getPhone());
			}
			for(XtglUseUnit unit: useUnits){
				lists.add(unit.getPhone());
			}
			for(XtglUsers user: users){
				lists.add(user.getPhone());
			}
			request.setAttribute("lists", lists);
			return	new ActionForward("/jsp/dtjk/phone/updatePhone.jsp");
		}else{
			return	new ActionForward("/jsp/dtjk/phone/detailPhone.jsp");
		}
	}
	
	
	/**
	 * ���� ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkPhone elevator =DtjkFrom.getPh();
		DtjkPhone entity=phoneService.get(elevator.getId());
		if(entity.getId()!=null){
			entity.setPhone(elevator.getPhone());
			phoneService.save(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ĵ��ݰ��������绰���룺"+elevator.getPhone(), "1");
	    return	new ActionForward("/phoneAction.do?method=query&elevatorId="+elevator.getElevatorId().getId());
	}
	
	
	/**
	 * ɾ������
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String elevatorId=request.getParameter("elevatorId");
		DtjkPhone entity=phoneService.get(id);
		phoneService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ�����ݰ��������绰���룺"+entity.getPhone(), "1");
		 return	new ActionForward("/phoneAction.do?method=query&elevatorId"+elevatorId);
	}
	/**
	 * ���� ɾ�� ����
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
				DtjkPhone entity=phoneService.get(Long.parseLong(arr[i]));
				phoneService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ�����ݰ��������绰���룺"+entity.getPhone(), "1");
			}
		}
		 return	new ActionForward("/phoneAction.do?method=query&elevatorId"+elevatorId);
		
	}
	
	/**
	 * ���� ���ݰ�������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String type=request.getParameter("type");
		String net=request.getParameter("net");
		
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(net!=null){
			 net=new String(net.getBytes("iso-8859-1"),"utf-8");
		 }
		DtjkPhone elevator=new DtjkPhone();
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "���ݰ�������Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			//phoneService.export(filePath, elevator);

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

	
		 return	null;
		
	}


}
