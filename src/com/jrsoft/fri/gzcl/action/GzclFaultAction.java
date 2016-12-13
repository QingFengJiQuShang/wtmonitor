package com.jrsoft.fri.gzcl.action;

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
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.entity.GzclRescue;
import com.jrsoft.fri.gzcl.from.GzclForm;
import com.jrsoft.fri.gzcl.service.GzclFaultService;
import com.jrsoft.fri.gzcl.service.GzclRescueService;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.service.XtglRescueUnitService;
import com.jrsoft.fri.xtsz.action.Log;

public class GzclFaultAction extends DispatchAction {
	
	private GzclFaultService faultService;
	private XtglRescueUnitService rescueUnitService;
	private GzclRescueService gzclRescueService;
	public GzclFaultService getFaultService() {
		return faultService;
	}

	public void setFaultService(GzclFaultService faultService) {
		this.faultService = faultService;
	}

	public XtglRescueUnitService getRescueUnitService() {
		return rescueUnitService;
	}

	public void setRescueUnitService(XtglRescueUnitService rescueUnitService) {
		this.rescueUnitService = rescueUnitService;
	}

	public GzclRescueService getGzclRescueService() {
		return gzclRescueService;
	}

	public void setGzclRescueService(GzclRescueService gzclRescueService) {
		this.gzclRescueService = gzclRescueService;
	}

	/**
	 * ���� ��ǰ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		GzclForm GzclForm=(GzclForm)form;
		GzclFault elevator =GzclForm.getFault();
		faultService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "��ӵ�ǰ����", "1");
		
	    return	new ActionForward("/faultAction.do?method=query");
	}
	/**
	 * ��ѯ ��ǰ�����б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String place=request.getParameter("place");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		String num=request.getParameter("num");   //��ǰҳ
		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
		
		List<GzclFault> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
		//��ѯ���񶩵�
		String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place ,xu.name as username  " +
				" from gzcl_fault de " +
				" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
				" left join xtgl_users xu on xu.id=de.duty_id "+  //������Ϣ
				"where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and e.registerid like '%"+registerid+"%'";
				}
				if(place!=null&&!place.equals("")){
					sql+=" and e.install_Place like '%"+place+"%'";
				}
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				sql+="  and de.state='������' order by de.happen_Time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<GzclFault>();
				while(rs.next()){
					GzclFault useUnit=new GzclFault();
					useUnit.setId(rs.getLong("id"));
					useUnit.setFault(rs.getString("fault"));
					useUnit.setHandle(rs.getString("handle"));
					useUnit.setHappenTime(rs.getString("happen_Time")==null?null:df.parse(rs.getString("happen_Time")));
					useUnit.setAlarmTime(rs.getString("alarm_Time")==null?null:df.parse(rs.getString("alarm_Time")));
					useUnit.setArriveTime(rs.getString("arrive_Time")==null?null:df.parse(rs.getString("arrive_Time")));
					useUnit.setSuccessTime(rs.getString("success_Time")==null?null:df.parse(rs.getString("success_Time")));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setDutyName(rs.getString("username"));
					useUnit.setNumbers(rs.getString("numbers"));
					useUnit.setState(rs.getString("state"));
					list.add(useUnit);
					
				}
				request.setAttribute("registerid", registerid);
				request.setAttribute("place", place);
				if(endtime!=null){
					request.setAttribute("endtime", df.parse( endtime));
				}
				if(begintime!=null){
					request.setAttribute("begintime", df.parse( begintime));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/gzgl/fault/faultList.jsp");
		}
	
	/**
	 * �༭ �鿴 ��ǰ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		GzclFault list=faultService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		List<XtglRescueUnit> unitId=new ArrayList<XtglRescueUnit>();
		String hql=" where  fault_Id='"+list.getId()+"'";
		List<GzclRescue> rescues=gzclRescueService.query(hql);
		for(int i=0;i<rescues.size();i++){
			unitId.add(rescues.get(i).getRescueUnitId());
		}
		request.setAttribute("unitId", unitId);
		
		if(flag.equals("1")){
			return	new ActionForward("/jsp/gzgl/fault/updateFault.jsp");
		}else{
			return	new ActionForward("/jsp/gzgl/fault/detailFault.jsp");
		}
	}
	
	/**
	 * �޸ĵ�ǰ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		GzclForm GzclForm=(GzclForm)form;
		GzclFault unit =GzclForm.getFault();
		GzclFault fault=faultService.get(unit.getId());
		if(fault!=null){
					String happenTime=request.getParameter("happenTime");
					String alarmTime=request.getParameter("alarmTime");
					String arriveTime=request.getParameter("arriveTime");
					String successTime=request.getParameter("successTime");
					SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					if(happenTime !=null&&!happenTime.equals(""))
						fault.setHappenTime(df.parse(happenTime));
					if(alarmTime !=null&&!alarmTime.equals(""))
						fault.setAlarmTime(df.parse(alarmTime));
					if(arriveTime !=null&&!arriveTime.equals(""))
						fault.setArriveTime(df.parse(arriveTime));
					if(successTime !=null&&!successTime.equals(""))
						fault.setSuccessTime(df.parse(successTime));
					fault.setFaultType(unit.getFaultType());
					fault.setFault(unit.getFault());
					fault.setNumbers(unit.getNumbers());
					fault.setFaultType(unit.getFaultType());
					fault.setFaultType(unit.getFaultType());
					faultService.update(fault);
		
				//ɾ�����еĹ�ϵ�����½�����ϵ
				String sql1="delete Gzcl_Rescue where fault_Id='"+unit.getId()+"'";
			   DBEntity.getInstance().executeSql(sql1);	
				String[] unitIds=request.getParameterValues("unitId");
				if(unitIds!=null){
					for(String u:unitIds){
						GzclRescue rescue=new GzclRescue();
						rescue.setFaultId(unit);
						rescue.setRescueUnitId(rescueUnitService.get(Long.parseLong(u)));
						gzclRescueService.save(rescue);
					}
				}
				
				
				if(!unit.getState().equals("������")){
					String sql="update dtjk_elevator set state='����' where id='"+unit.getElevatorId().getId()+"' ";
					DBEntity.getInstance().executeSql(sql);
				}
				//DtjkElevator elevator=e
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "�޸ĵ�ǰ����", "1");
			}
		return	new ActionForward("/faultAction.do?method=query");
	}
	
	
	
	
	/**
	 * ɾ����ǰ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		faultService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ����ǰ����", "1");
		 return	new ActionForward("/faultAction.do?method=query");
	}
	/**
	 * ���� ɾ�� ��ǰ����
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
				faultService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ����ǰ����", "1");
			}
		}
		 return	new ActionForward("/faultAction.do?method=query");
		
	}
	
	/**
	 * ���� ��ǰ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String registerid=request.getParameter("registerid");
		String place=request.getParameter("place");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		GzclFault fault=new GzclFault();
		 if(registerid!=null)
			 fault.setRegisterid(registerid);
		 if(place!=null)
			 fault.setPlace(place);
		 if(begintime!=null)
			 fault.setBegintime(begintime);
		 if(endtime!=null)	 
			 fault.setEndtime(endtime);
		 fault.setState("='������'");
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "��ǰ������Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			faultService.export(filePath, fault);

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
	/**
	 * ���� ��ʷ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String registerid=request.getParameter("registerid");
		String place=request.getParameter("place");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		GzclFault fault=new GzclFault();
		 if(registerid!=null)
			 fault.setRegisterid(registerid);
		 if(place!=null)
			 fault.setPlace(place);
		 if(begintime!=null)
			 fault.setBegintime(begintime);
		 if(endtime!=null)	 
			 fault.setEndtime(endtime);
		 fault.setState("!='������'");
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "��ʷ������Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			faultService.export(filePath, fault);

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
	
	/**
	 * ��ѯ ��ʷ�����б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String registerid=request.getParameter("registerid");
		String place=request.getParameter("place");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
		
		List<GzclFault> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
		//��ѯ
		String sql="select de.*,e.registerid as registerid,e.distinguishid as distinguishid,e.install_place as place ,xu.name as username  " +
				" from gzcl_fault de " +
				" left join dtjk_elevator e on e.id=de.elevator_id "+  //������Ϣ
				" left join xtgl_users xu on xu.id=de.duty_id "+  //������Ϣ
				"where  1=1 " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and e.registerid like '%"+registerid+"%'";
				}
				if(place!=null&&!place.equals("")){
					sql+=" and e.install_Place like '%"+place+"%'";
				}
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.happen_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.happen_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				sql+="  and de.state!='������' order by de.happen_Time desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<GzclFault>();
				while(rs.next()){
					GzclFault useUnit=new GzclFault();
					useUnit.setId(rs.getLong("id"));
					useUnit.setFault(rs.getString("fault"));
					useUnit.setHandle(rs.getString("handle"));
					useUnit.setHappenTime(rs.getString("happen_Time")==null?null:df.parse(rs.getString("happen_Time")));
					useUnit.setAlarmTime(rs.getString("alarm_Time")==null?null:df.parse(rs.getString("alarm_Time")));
					useUnit.setArriveTime(rs.getString("arrive_Time")==null?null:df.parse(rs.getString("arrive_Time")));
					useUnit.setSuccessTime(rs.getString("success_Time")==null?null:df.parse(rs.getString("success_Time")));
					useUnit.setRegisterid(rs.getString("registerid"));
					useUnit.setDistinguishid(rs.getString("distinguishid"));
					useUnit.setPlace(rs.getString("place"));
					useUnit.setDutyName(rs.getString("username"));
					useUnit.setNumbers(rs.getString("numbers"));
					useUnit.setState(rs.getString("state"));
					list.add(useUnit);
					
				}
				request.setAttribute("registerid", registerid);
				request.setAttribute("place", place);
				if(endtime!=null){
					request.setAttribute("endtime", df.parse( endtime));
				}
				if(begintime!=null){
					request.setAttribute("begintime", df.parse( begintime));
				}
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/gzgl/fault/faultList1.jsp");
		}
}
