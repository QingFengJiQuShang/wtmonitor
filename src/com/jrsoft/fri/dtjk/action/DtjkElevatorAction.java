package com.jrsoft.fri.dtjk.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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

import net.sf.json.JSON;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;
import com.jrsoft.fri.dtjk.from.DtjkFrom;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkGatewayService;
import com.jrsoft.fri.dtjk.service.DtjkPhoneService;
import com.jrsoft.fri.xtgl.action.Authority;
import com.jrsoft.fri.xtgl.action.Upload;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;
import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtgl.service.XtglMakeUnitService;
import com.jrsoft.fri.xtgl.service.XtglPropertyUnitService;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtsz.action.Log;
import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class DtjkElevatorAction extends DispatchAction{
	private DtjkElevatorService elevatorService;
	private DtjkGatewayService gatewayService;
	private DtjkPhoneService phoneService;
	private XtglMaintenanceUnitService maintenanceUnitService;
	private XtglMaintenanceUsersService maintenanceUsersService;
	private XtglPropertyUnitService propertyUnitService;
	private XtglUseUnitService useUnitService;
	private XtglMakeUnitService makeUnitService;
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

	public DtjkPhoneService getPhoneService() {
		return phoneService;
	}

	public void setPhoneService(DtjkPhoneService phoneService) {
		this.phoneService = phoneService;
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

	public XtglPropertyUnitService getPropertyUnitService() {
		return propertyUnitService;
	}

	public void setPropertyUnitService(XtglPropertyUnitService propertyUnitService) {
		this.propertyUnitService = propertyUnitService;
	}

	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}

	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}

	public XtglMakeUnitService getMakeUnitService() {
		return makeUnitService;
	}

	public void setMakeUnitService(XtglMakeUnitService makeUnitService) {
		this.makeUnitService = makeUnitService;
	}

	/**
	   * ��֤ ע���Ψһ
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void onlyRegisterid(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String registerid=request.getParameter("registerid");   //�û���
				//��ѯע���
				String hql=" where 1=1   and de.delflag!='1' and   registerid = '"+registerid+"'  order by id asc ";
				List<DtjkElevator> content=elevatorService.query(hql);	
				PrintWriter out;
				try {
					if(content.size()>0){
						out = response.getWriter();
						out.write("0"); 
					}else{
						out = response.getWriter();
						out.write("1"); 
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		String installTime=request.getParameter("installTime");
		String manufactureTime=request.getParameter("manufactureTime");
		String yearlyTime1=request.getParameter("yearlyTime1");
		String flowStart=request.getParameter("flowStart");
		String flowEnd=request.getParameter("flowEnd");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		elevator.setUserid(user);
		
		if(elevator.getUseUnitId().getId()==0)
			elevator.setUseUnitId(null);
		if(elevator.getGatewayId().getId()==0)
			elevator.setGatewayId(null);
		if(elevator.getMaintenanceUsersId().getId()==0)
			elevator.setMaintenanceUsersId(null);
		if(elevator.getMaintenanceUnitId().getId()==0)
			elevator.setMaintenanceUnitId(null);
		if(manufactureTime!=null&&!manufactureTime.equals("") )
			elevator.setManufactureTime(df.parse(manufactureTime));
		if(installTime!=null&&!installTime.equals("") )
			elevator.setInstallTime(df.parse(installTime));
		if(yearlyTime1!=null&&!yearlyTime1.equals("") )
			elevator.setYearlyTime1(df.parse(yearlyTime1));
		if(flowStart!=null&&!flowStart.equals("") )
			elevator.setFlowStart(df.parse(flowStart));
		if(flowEnd!=null&&!flowEnd.equals("") )
			elevator.setFlowEnd(df.parse(flowEnd));
		elevator.setState("����");
		elevator.setPeriod("60");
		elevator.setDelflag("0");
		elevatorService.save(elevator);
		
		System.out.println(request.getRemoteAddr());
		
		//����Ĭ�ϰ�����
		
		if(elevator.getUseUnitId()!=null){
			DtjkPhone phone=new DtjkPhone();
			XtglUseUnit unit=useUnitService.get(elevator.getUseUnitId().getId());
			phone.setElevatorId(elevator);
			phone.setBelong("ʹ�õ�λ������");
			phone.setPhone(unit.getPhone());
			phoneService.save(phone);
		}
		if(elevator.getPropertyUnitId()!=null){
			XtglPropertyUnit unit=propertyUnitService.get(elevator.getPropertyUnitId().getId());
			DtjkPhone phone=new DtjkPhone();
			phone.setElevatorId(elevator);
			phone.setBelong("��ҵ��λ������");
			phone.setPhone(unit.getPhone());
			phoneService.save(phone);
		}
		if(elevator.getMaintenanceUnitId()!=null){
			XtglMaintenanceUnit unit=maintenanceUnitService.get(elevator.getMaintenanceUnitId().getId());

			DtjkPhone phone=new DtjkPhone();
			phone.setElevatorId(elevator);
			phone.setBelong("ά����λ������");
			phone.setPhone(unit.getPhone());
			phoneService.save(phone);
		}
		if(elevator.getMaintenanceUsersId()!=null){
			XtglMaintenanceUsers unit=maintenanceUsersService.get(elevator.getMaintenanceUsersId().getId());

			DtjkPhone phone=new DtjkPhone();
			phone.setElevatorId(elevator);
			phone.setBelong("ά����Ա");
			phone.setPhone(unit.getPhone());
			phoneService.save(phone);
		}
		//���� ������־
		Log log=new Log();
        log.addLog(user.getName(), "��ӵ��ݣ�����ע��ţ�"+elevator.getRegisterid(), "1");
	    return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	/**
	 * �жϵ����Ƿ�����
	 * @throws Exception 
	 */
	public void judge( ) throws Exception{
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.MINUTE, -3);							//3����ǰ
		String sql="update DTJK_ELEVATOR set   state='����' where state='����' and ( report_Time is null or report_Time<=to_date('" + df.format(c.getTime())+ "','yyyy-MM-dd hh24:mi:ss') ) and delflag!='1'  ";
		DBEntity.getInstance().executeSql(sql);
		
	}
	/**
	 * ��ѯ �����б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String maintenanceUnitName=request.getParameter("maintenanceUnitName");
		String propertyUnitName=request.getParameter("propertyUnitName");
		String makeUnitName=request.getParameter("makeUnitName");
		String installPlace=request.getParameter("installPlace");
		 judge( ) ;	//�жϵ����Ƿ�����

		String num=request.getParameter("num");   //��ǰҳ
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		System.out.println(Authority.Sql(user));
		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName,xpu.name as propertyUnitName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����
						" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //��ҵ��λ
						" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //���쵥λ
						" where  1=1  and de.delflag!='1' " ;
				if(province!=null&&!province.equals("")){
					province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.province  ='" + province+ "'";
				}
				if(city!=null&&!city.equals("")){
					city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.city ='" + city+ "'";
				}
				if(area!=null&&!area.equals("")){
					area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.area  ='" + area+ "'";
				}
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					useUnitName=new String(useUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(maintenanceUnitName!=null&&!maintenanceUnitName.equals("")){
					maintenanceUnitName=new String(maintenanceUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xmu.name like '%"+maintenanceUnitName+"%'";
				}
				if(propertyUnitName!=null&&!propertyUnitName.equals("")){
					propertyUnitName=new String(propertyUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xpu.name like '%"+propertyUnitName+"%'";
				}
				if(makeUnitName!=null&&!makeUnitName.equals("")){
					makeUnitName=new String(makeUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and make.name like '%"+makeUnitName+"%'";
				}
				if(installPlace!=null&&!installPlace.equals("")){
					installPlace=new String(installPlace.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
				SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
					if(rs.getString("next_time")==null||df.parse(rs.getString("next_time")).getTime()<=(new Date()).getTime()){
						elevator.setYearlyState("�����");
					}else{
						elevator.setYearlyState("�����");
					}
					
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					elevator.setPropertyUnitName(rs.getString("propertyUnitName"));
					elevator.setType(rs.getString("type"));
					elevator.setPeriod(rs.getString("period"));
					elevator.setFlowSurplus(rs.getLong("flow_Surplus"));
					String sql2="select count(*)  from dtjk_phone de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					String sql3="select count(*)  from Dtjk_Service de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					 n=DBEntity.getInstance().queryDataCount(sql3);
					elevator.setNumService(n);
					list.add(elevator);
					
				}
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("province",province );
				request.setAttribute("city",city );
				request.setAttribute("area",area );
				request.setAttribute("maintenanceUnitName", maintenanceUnitName);
				request.setAttribute("propertyUnitName", propertyUnitName);
				request.setAttribute("makeUnitName", makeUnitName);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/elevator/elevatorList.jsp");
		}
	
	/**
	 * ��ѯ �����б�  ���ݼ��
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryMonitor(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String maintenanceUnitName=request.getParameter("maintenanceUnitName");
		String propertyUnitName=request.getParameter("propertyUnitName");
		String makeUnitName=request.getParameter("makeUnitName");
		String installPlace=request.getParameter("installPlace");
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName, xpu.name as propertyUnitName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����
						" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //��ҵ��λ
						" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //���쵥λ
						" where  1=1   and de.delflag!='1' " ;
				if(province!=null&&!province.equals("")){
					province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.province  ='" + province+ "'";
				}
				if(city!=null&&!city.equals("")){
					city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.city ='" + city+ "'";
				}
				if(area!=null&&!area.equals("")){
					area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.area  ='" + area+ "'";
				}
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					useUnitName=new String(useUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(maintenanceUnitName!=null&&!maintenanceUnitName.equals("")){
					maintenanceUnitName=new String(maintenanceUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xmu.name like '%"+maintenanceUnitName+"%'";
				}
				if(propertyUnitName!=null&&!propertyUnitName.equals("")){
					propertyUnitName=new String(propertyUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xpu.name like '%"+propertyUnitName+"%'";
				}
				if(makeUnitName!=null&&!makeUnitName.equals("")){
					makeUnitName=new String(makeUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and make.name like '%"+makeUnitName+"%'";
				}
				if(installPlace!=null&&!installPlace.equals("")){
					installPlace=new String(installPlace.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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

					
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("province",province );
				request.setAttribute("city",city );
				request.setAttribute("area",area );
				request.setAttribute("maintenanceUnitName", maintenanceUnitName);
				request.setAttribute("propertyUnitName", propertyUnitName);
				request.setAttribute("makeUnitName", makeUnitName);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/monitor/monitorList.jsp");
		}
	
	/**
	 * ��ѯ �����б�  ���ݼ��
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryPlayback (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String maintenanceUnitName=request.getParameter("maintenanceUnitName");
		String propertyUnitName=request.getParameter("propertyUnitName");
		String makeUnitName=request.getParameter("makeUnitName");
		String installPlace=request.getParameter("installPlace");
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����
						" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //��ҵ��λ
						" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //���쵥λ
						" where  1=1   and de.delflag!='1' " ;
				if(province!=null&&!province.equals("")){
					province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.province  ='" + province+ "'";
				}
				if(city!=null&&!city.equals("")){
					city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.city ='" + city+ "'";
				}
				if(area!=null&&!area.equals("")){
					area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.area  ='" + area+ "'";
				}
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					useUnitName=new String(useUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(maintenanceUnitName!=null&&!maintenanceUnitName.equals("")){
					maintenanceUnitName=new String(maintenanceUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xmu.name like '%"+maintenanceUnitName+"%'";
				}
				if(propertyUnitName!=null&&!propertyUnitName.equals("")){
					propertyUnitName=new String(propertyUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xpu.name like '%"+propertyUnitName+"%'";
				}
				if(makeUnitName!=null&&!makeUnitName.equals("")){
					makeUnitName=new String(makeUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and make.name like '%"+makeUnitName+"%'";
				}
				if(installPlace!=null&&!installPlace.equals("")){
					installPlace=new String(installPlace.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
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
					elevator.setInstallPlace(rs.getString("install_Place"));
					String sql2="select count(*)  from dtjk_record de where  1=1  and elevator_id = '"+rs.getString("registerid")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("province",province );
				request.setAttribute("city",city );
				request.setAttribute("area",area );
				request.setAttribute("maintenanceUnitName", maintenanceUnitName);
				request.setAttribute("propertyUnitName", propertyUnitName);
				request.setAttribute("makeUnitName", makeUnitName);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/playback/elevatorList.jsp");
		}
	
	/**
	 * ��ѯ ���ݹ���  
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  queryManage (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String registerid=request.getParameter("registerid");
		String distinguishid=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String maintenanceUnitName=request.getParameter("maintenanceUnitName");
		String propertyUnitName=request.getParameter("propertyUnitName");
		String makeUnitName=request.getParameter("makeUnitName");
		String installPlace=request.getParameter("installPlace");
		String yearlyState=request.getParameter("yearlyState");
		String serviceState=request.getParameter("serviceState");
		String num=request.getParameter("num");   //��ǰҳ
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ʹ�õ�λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����
						" left join Xtgl_Property_Unit xpu on xpu.id=de.property_Unit_Id"+  //��ҵ��λ
						" left join Xtgl_Make_Unit make on make.id=de.make_Unit_Id"+  //���쵥λ
						" where  1=1   and de.delflag!='1' " ;
				if(province!=null&&!province.equals("")){
					province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.province  ='" + province+ "'";
				}
				if(city!=null&&!city.equals("")){
					city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.city ='" + city+ "'";
				}
				if(area!=null&&!area.equals("")){
					area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.area  ='" + area+ "'";
				}
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(distinguishid!=null&&!distinguishid.equals("")){
					sql+=" and de.distinguishid like '%"+distinguishid+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					useUnitName=new String(useUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				if(maintenanceUnitName!=null&&!maintenanceUnitName.equals("")){
					maintenanceUnitName=new String(maintenanceUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xmu.name like '%"+maintenanceUnitName+"%'";
				}
				if(propertyUnitName!=null&&!propertyUnitName.equals("")){
					propertyUnitName=new String(propertyUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and xpu.name like '%"+propertyUnitName+"%'";
				}
				if(makeUnitName!=null&&!makeUnitName.equals("")){
					makeUnitName=new String(makeUnitName.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and make.name like '%"+makeUnitName+"%'";
				}
				if(installPlace!=null&&!installPlace.equals("")){
					installPlace=new String(installPlace.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				if(serviceState!=null&&!serviceState.equals("")){
					sql+=" and de.service_State = '"+serviceState+"'";
				}
				if(yearlyState!=null&&!yearlyState.equals("")){
					if(yearlyState.equals("0")){
						sql+=" and (de.next_Time  <=to_date('" + df.format(new Date())+ "','yyyy-MM-dd hh24:mi:ss') or de.next_Time is null )";
					}else{
						sql+=" and de.next_Time  >=to_date('" + df.format(new Date())+ "','yyyy-MM-dd hh24:mi:ss')";
					}
				}
				sql+=" order by de.next_Time ";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	
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
					elevator.setPeriod(rs.getString("period")==null?"0":rs.getString("period"));
					elevator.setFlowSurplus(rs.getLong("flow_Surplus"));
					elevator.setServiceState(rs.getString("service_State"));
					elevator.setNextTime(rs.getString("next_Time")==null?null:df.parse(rs.getString("next_Time")));
					
					String sql2="select count(*)  from dtjk_phone de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					String sql3="select count(*)  from Dtjk_Service de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					 n=DBEntity.getInstance().queryDataCount(sql3);
					elevator.setNumService(n);
					 sql2="select count(*)  from dtjk_maintenance_records de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					 n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNumRecords(n);
					sql2="select count(*)  from dtjk_yearly_inspection de where  1=1  and elevator_id = '"+rs.getString("id")+"'";
					n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNumYearly(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("registerid", registerid);
				request.setAttribute("distinguishid", distinguishid);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("province",province );
				request.setAttribute("city",city );
				request.setAttribute("area",area );
				request.setAttribute("maintenanceUnitName", maintenanceUnitName);
				request.setAttribute("propertyUnitName", propertyUnitName);
				request.setAttribute("makeUnitName", makeUnitName);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("yearlyState", yearlyState);
				request.setAttribute("serviceState", serviceState);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dtjk/manage/manageList.jsp");
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
		DtjkElevator list=elevatorService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/dtjk/elevator/updateElevator.jsp");
		}else if(flag.equals("3")){
			return	new ActionForward("/jsp/dtjk/period/period.jsp");
		}else if(flag.equals("4")){
			return	new ActionForward("/jsp/dtjk/elevator/flow.jsp");
		}else{
			String sql2="select count(*)  from dtjk_phone de where  1=1  and elevator_id = '"+list.getId()+"'";
			int n=DBEntity.getInstance().queryDataCount(sql2);
			list.setNum(n);
			String sql3="select count(*)  from Dtjk_Service de where  1=1  and elevator_id = '"+list.getId()+"'";
			 n=DBEntity.getInstance().queryDataCount(sql3);
			 list.setNumService(n);
			//�����ն˺Ų�ѯ���ն��Ƿ��Ѽ�¼
			String hql=" where  1=1 and elevatorId='"+list.getRegisterid()+"' " ;
			List<DtjkGateway> gateways=gatewayService.queryAll(hql);
			if(gateways.size()>0){
				request.setAttribute("gateway", gateways.get(0));
			}
			
			return	new ActionForward("/jsp/dtjk/elevator/detailElevator.jsp");
		}
	}
	
	/**
	 * �޸ĵ���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String installTime=request.getParameter("installTime");
		String manufactureTime=request.getParameter("manufactureTime");
		String flowStart=request.getParameter("flowStart");
		String flowEnd=request.getParameter("flowEnd");
		String yearlyTime1=request.getParameter("yearlyTime1");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		DtjkElevator entity =elevatorService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setRegisterid(elevator.getRegisterid());
			entity.setDistinguishid(elevator.getDistinguishid());
			entity.setBrand(elevator.getBrand());
			entity.setModel(elevator.getModel());
			//entity.setState(elevator.getState());
			entity.setType(elevator.getType());
			entity.setNumbers(elevator.getNumbers());
			entity.setLabel(elevator.getLabel());
			entity.setPlace(elevator.getPlace());
			entity.setGatewayId(elevator.getGatewayId());
			entity.setUseUnitId(elevator.getUseUnitId());
			entity.setMaintenanceUnitId(elevator.getMaintenanceUnitId());
			entity.setRegisterState(elevator.getRegisterState());
			entity.setSpeed(elevator.getSpeed());
			entity.setMaintenanceUsersId(elevator.getMaintenanceUsersId());
			entity.setInstallPlace(elevator.getInstallPlace());
			entity.setInstallUnit(elevator.getInstallUnit());
			entity.setInstallUser(elevator.getInstallUser());
			entity.setServiceIfe(elevator.getServiceIfe());
			entity.setRemarks(elevator.getRemarks());
			entity.setPropertyUnitId(elevator.getPropertyUnitId());
			
			entity.setUseUnitLiaisons(elevator.getUseUnitLiaisons());
			entity.setUseUnitPhone(elevator.getUseUnitPhone());
			entity.setPropertyUnitLiaisons(elevator.getPropertyUnitLiaisons());
			entity.setPropertyUnitPhone(elevator.getPropertyUnitPhone());
			entity.setIp(elevator.getIp());
			entity.setProvince(elevator.getProvince());
			entity.setCity(elevator.getCity());
			entity.setArea(elevator.getArea());
			entity.setMakeUnitId(elevator.getMakeUnitId());
			if(elevator.getUseUnitId().getId()==0)
				entity.setUseUnitId(null);
			if(elevator.getGatewayId().getId()==0)
				entity.setGatewayId(null);
			if(elevator.getMaintenanceUsersId().getId()==0)
				entity.setMaintenanceUsersId(null);
			if(elevator.getMaintenanceUnitId().getId()==0)
				entity.setMaintenanceUnitId(null);
			if(manufactureTime!=null&&!manufactureTime.equals("") )
				entity.setManufactureTime(df.parse(manufactureTime));
			if(installTime!=null&&!installTime.equals("") )
				entity.setInstallTime(df.parse(installTime));
			if(yearlyTime1!=null&&!yearlyTime1.equals("") )
				entity.setYearlyTime1(df.parse(yearlyTime1));
			if(flowStart!=null&&!flowStart.equals("") )
				entity.setFlowStart(df.parse(flowStart));
			if(flowEnd!=null&&!flowEnd.equals("") )
				entity.setFlowEnd(df.parse(flowEnd));
			elevatorService.update(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ĵ��ݣ�����ע��ţ�"+elevator.getRegisterid(), "1");
		return	new ActionForward("/elevatorAction.do?method=query");
	}
	
	/**
	 * �޸ĵ����ϱ�����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updatePeriod(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		DtjkElevator entity =elevatorService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setPeriod(elevator.getPeriod());
			elevatorService.update(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ĵ����ϱ����ڣ�����ע��ţ�"+elevator.getRegisterid(), "1");
		return	new ActionForward("/elevatorAction.do?method=queryManage");
	}
	
	/**
	 * �޸ĵ��� ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateFlow(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String flowStart=request.getParameter("flowStart");
		String flowEnd=request.getParameter("flowEnd");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		DtjkFrom DtjkFrom=(DtjkFrom)form;
		DtjkElevator elevator =DtjkFrom.getElevator();
		DtjkElevator entity =elevatorService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setFlowTotal(elevator.getFlowTotal());
			entity.setFlowNum(elevator.getFlowNum());
			entity.setFlowSurplus(elevator.getFlowSurplus());
			if(flowStart!=null&&!flowStart.equals("") )
				entity.setFlowStart(df.parse(flowStart));
			if(flowEnd!=null&&!flowEnd.equals("") )
				entity.setFlowEnd(df.parse(flowEnd));
			elevatorService.update(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ĵ�������������ע��ţ�"+elevator.getRegisterid(), "1");
		return	new ActionForward("/elevatorAction.do?method=queryManage");
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
		DtjkElevator entity =elevatorService.get(id);
		//elevatorService.delete(id);
		entity.setDelflag("1");
		elevatorService.update(entity);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ�����ݣ�����ע��ţ�"+entity.getRegisterid(), "1");
		 return	new ActionForward("/elevatorAction.do?method=query");
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
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				DtjkElevator entity =elevatorService.get(Long.parseLong(arr[i]));
				//elevatorService.delete(Long.parseLong(arr[i]));
				entity.setDelflag("1");
				elevatorService.update(entity);
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ�����ݣ�����ע��ţ�"+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/elevatorAction.do?method=query");
		
	}
	
	/**
	 * ���� ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String register=request.getParameter("registerid");
		String distinguish=request.getParameter("distinguishid");
		String useUnitName=request.getParameter("useUnitName");
		String brand=request.getParameter("brand");
		String numbers=request.getParameter("numbers");
		String SafeState=request.getParameter("SafeState");
		DtjkElevator elevator=new DtjkElevator();
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

		 if(register!=null){
			 register=new String(register.getBytes("iso-8859-1"),"utf-8");
			 elevator.setRegisterid(register);
		 }
		 if(distinguish!=null){
			 distinguish=new String(distinguish.getBytes("iso-8859-1"),"utf-8");
			 elevator.setDistinguishid(distinguish);
		 }
		 if(useUnitName!=null){
			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
			 elevator.setUseUnitName(useUnitName);
		 }
		 if(brand!=null){
			 brand=new String(brand.getBytes("iso-8859-1"),"utf-8");
			 elevator.setBrand(brand);
		 }
		 if(numbers!=null){
			 numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
			 elevator.setNumbers(numbers);
		 }
		 if(SafeState!=null){
			 elevator.setSafeState(SafeState);
		 }
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "������Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			elevatorService.export(filePath, elevator,Authority.Sql(user));

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
	
	/**
	 * ѡ�� �����б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		String id2=request.getParameter("id2");
		String id3=request.getParameter("id3");
		String id4=request.getParameter("id4");
		String registerid=request.getParameter("registerid");
		String installPlace=request.getParameter("installPlace");
		String useUnitName=request.getParameter("useUnitName");
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

//		 if(registerid!=null){
//			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
//		 }
//		 if(useUnitName!=null){
//			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
//		 }
//		 if(installPlace!=null){
//			 installPlace=new String(installPlace.getBytes("iso-8859-1"),"utf-8");
//		 }
		String num=request.getParameter("num");   //��ǰҳ

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ά����λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����λ
						" where  1=1   and de.delflag!='1' " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(installPlace!=null&&!installPlace.equals("")){
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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
					elevator.setInstallPlace(rs.getString("install_Place"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					list.add(elevator);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("id2", id2);
				request.setAttribute("id3", id3);
				request.setAttribute("id4", id4);
				request.setAttribute("registerid", registerid);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/comm/selectElevator.jsp");
		}

	/**
	 * ѡ�� �����б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		String id2=request.getParameter("id2");
		String id3=request.getParameter("id3");
		String id4=request.getParameter("id4");
		String id5=request.getParameter("id5");
		String id6=request.getParameter("id6");
		String id7=request.getParameter("id7");
		String registerid=request.getParameter("registerid");
		String installPlace=request.getParameter("installPlace");
		String useUnitName=request.getParameter("useUnitName");
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

//		 if(registerid!=null){
//			 registerid=new String(registerid.getBytes("iso-8859-1"),"utf-8");
//		 }
//		 if(useUnitName!=null){
//			 useUnitName=new String(useUnitName.getBytes("iso-8859-1"),"utf-8");
//		 }
//		 if(installPlace!=null){
//			 installPlace=new String(installPlace.getBytes("iso-8859-1"),"utf-8");
//		 }
		String num=request.getParameter("num");   //��ǰҳ

		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkElevator> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*,xuu.name as useUnitName, xmu.name as  maintenanceUnitName,xuu.id as useUnitName1, xmu.id as  maintenanceUnitName1,mu.id as maintenanceUsersName1,mu.name as maintenanceUsersName" +
						" from ("+Authority.Sql(user)+") de " +
						" left join xtgl_use_unit xuu on xuu.id=de.use_unit_id "+  //ά����λ
						" left join xtgl_maintenance_unit xmu on xmu.id=de.maintenance_unit_id"+  //ά����λ
						" left join xtgl_maintenance_users mu on mu.id=de.maintenance_users_id"+  //ά����λ
						" where  1=1   and de.delflag!='1' " ;
				if(registerid!=null&&!registerid.equals("")){
					sql+=" and de.registerid like '%"+registerid+"%'";
				}		
				if(installPlace!=null&&!installPlace.equals("")){
					sql+=" and de.install_Place like '%"+installPlace+"%'";
				}
				if(useUnitName!=null&&!useUnitName.equals("")){
					sql+=" and xuu.name like '%"+useUnitName+"%'";
				}
				sql+=" order by de.id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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
					elevator.setInstallPlace(rs.getString("install_Place"));
					elevator.setManufactureTime(rs.getDate("manufacture_Time"));
					elevator.setYearlyState(rs.getString("yearly_State"));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					elevator.setUseUnitName(rs.getString("useunitname"));
					elevator.setUseUnitName1(rs.getString("useunitname1"));
					elevator.setMaintenanceUnitName1(rs.getString("maintenanceUnitName1"));
					elevator.setMaintenanceUnitName(rs.getString("maintenanceUnitName"));
					elevator.setMaintenanceUsersName1(rs.getString("maintenanceUsersName1"));
					elevator.setMaintenanceUsersName(rs.getString("maintenanceUsersName"));
					list.add(elevator);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("id2", id2);
				request.setAttribute("id3", id3);
				request.setAttribute("id4", id4);
				request.setAttribute("id5", id5);
				request.setAttribute("id6", id6);
				request.setAttribute("id7", id7);
				request.setAttribute("registerid", registerid);
				request.setAttribute("installPlace", installPlace);
				request.setAttribute("useUnitName", useUnitName);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/comm/selectElevator1.jsp");
		}
	/**
	 * ���� ������ֵ
	 */
	public ActionForward  rechargeEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		Long num=Long.parseLong(request.getParameter("num"));
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				DtjkElevator entity =elevatorService.get(Long.parseLong(arr[i]));
				entity.setFlowSurplus((entity.getFlowSurplus()==null?0:entity.getFlowSurplus())+num);
				elevatorService.update(entity);
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "��ֵ�Ե�������������ע��ţ�"+entity.getRegisterid(), "1");
			}
		}
		 return	new ActionForward("/elevatorAction.do?method=query");
		
	}
	
	/**
	 *   excel�������ݵ���
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  exportIn(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String pathname = request.getRealPath("/")+"upload\\";
		Upload upload = new Upload();
		DtjkFrom f = (DtjkFrom)form;
		FormFile file = f.getTheFile();
		String fileName=upload.uploadFile(file, request, pathname);
		InputStream in = new FileInputStream(fileName);
	    Workbook workbook = WorkbookFactory.create(in);  
	    Sheet sheet = workbook.getSheetAt(0);  //ʾ�����sheet  
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");

		for(int r=1; r<totalRows; r++) 
		{
			DtjkElevator entity =new DtjkElevator();
			row = sheet.getRow(r);
			entity.setRegisterid(row.getCell(0)==null?null:row.getCell(0).getStringCellValue());
			entity.setDistinguishid(row.getCell(1)==null?null:row.getCell(1).getStringCellValue());
			entity.setBrand(row.getCell(2)==null?null:row.getCell(2).getStringCellValue());
			entity.setModel(row.getCell(3)==null?null:row.getCell(3).getStringCellValue());
			entity.setType(row.getCell(4)==null?null:row.getCell(4).getStringCellValue());   		//��������
			entity.setSpeed(row.getCell(5)==null?null:row.getCell(5).getStringCellValue());   		//�����ٶ�
			entity.setNumbers(row.getCell(6)==null?null:row.getCell(6).getStringCellValue());   		//�ܲ���
			entity.setRegisterState(row.getCell(7)==null?null:row.getCell(7).getStringCellValue());   		//ע��״̬
			entity.setLabel(row.getCell(8)==null?null:row.getCell(8).getStringCellValue());   		//��ͼ��ע
			entity.setIp(row.getCell(9)==null?null:row.getCell(9).getStringCellValue());					//��Ƶip
			entity.setFlowStart(row.getCell(10)==null?null:df.parse(row.getCell(10).getStringCellValue()));					//��Ƶip
			entity.setFlowEnd(row.getCell(11)==null?null:df.parse(row.getCell(11).getStringCellValue()));					//��Ƶip
			entity.setProvince(row.getCell(12)==null?null:row.getCell(12).getStringCellValue());   		//ʡ
			entity.setCity(row.getCell(13)==null?null:row.getCell(13).getStringCellValue());   		//��
			entity.setArea(row.getCell(14)==null?null:row.getCell(14).getStringCellValue());   		//��
			
			//ʹ�õ�λ ��ѯ
			XtglUseUnit useUnit=null;
			if(row.getCell(15)!=null&&!row.getCell(15).getStringCellValue().equals("")){
				String hql=" where 1=1 and name='"+row.getCell(15).getStringCellValue()+"'";
				useUnit=useUnitService.query(hql).get(0);
			}
			entity.setUseUnitId(useUnit);   		//ʹ�õ�λ����
			entity.setUseUnitLiaisons(row.getCell(16)==null?null:row.getCell(16).getStringCellValue());   		//ʹ�õ�λ������
			entity.setUseUnitPhone(row.getCell(17)==null?null:row.getCell(17).getStringCellValue());   		//ʹ�õ�λ�����˵绰
			
			//���쵥λ��ѯ
			XtglMakeUnit makeUnit=null;
			if(row.getCell(18)!=null&&!row.getCell(18).getStringCellValue().equals("")){
				String hql=" where 1=1 and name='"+row.getCell(18).getStringCellValue()+"'";
				List<XtglMakeUnit> unit=makeUnitService.query(hql);
				if(unit.size()>0)makeUnit=unit.get(0);
			}
			entity.setMakeUnitId(makeUnit);   			//���쵥λ

			//��ҵ��λ��ѯ
			XtglPropertyUnit propertyUnit=null;
			if(row.getCell(19)!=null&&!row.getCell(19).getStringCellValue().equals("")){
				String hql=" where 1=1 and name='"+row.getCell(19).getStringCellValue()+"'";
				List<XtglPropertyUnit> unit=propertyUnitService.query(hql);
				if(unit.size()>0)propertyUnit=unit.get(0);
			}
			entity.setPropertyUnitId(propertyUnit);   		//��ҵ��λ����
			entity.setPropertyUnitLiaisons(row.getCell(20)==null?null:row.getCell(20).getStringCellValue());   		//��ҵ��λ������
		
			entity.setUseUnitPhone(row.getCell(21)==null?"":row.getCell(21).getStringCellValue());   				//��ҵ��λ�����˵绰
			//ά����λ
			XtglMaintenanceUnit maintenanceUnit=null;
			if(row.getCell(22)!=null&&!row.getCell(22).getStringCellValue().equals("")){
				String hql=" where 1=1 and name='"+row.getCell(22).getStringCellValue()+"'";
				List<XtglMaintenanceUnit> unit=maintenanceUnitService.query(hql);
				if(unit.size()>0)maintenanceUnit=unit.get(0);
			}
			entity.setMaintenanceUnitId(maintenanceUnit);		//ά����λ
			//ά����λ
			XtglMaintenanceUsers maintenanceUsers=null;
			if(row.getCell(23)!=null&&!row.getCell(23).getStringCellValue().equals("")){
				String hql=" where 1=1 and name='"+row.getCell(23).getStringCellValue()+"'";
				List<XtglMaintenanceUsers> unit=maintenanceUsersService.query(hql);
				if(unit.size()>0)maintenanceUsers=unit.get(0);
			}
			entity.setMaintenanceUsersId(maintenanceUsers);		//ά����Ա

			entity.setInstallPlace(row.getCell(24)==null?null:row.getCell(24).getStringCellValue());   		//��װ�ص�
			entity.setInstallUnit(row.getCell(25)==null?null:row.getCell(25).getStringCellValue());   		//��װ��λ
			entity.setInstallUser(row.getCell(26)==null?null:row.getCell(26).getStringCellValue());   		//��װ��
			entity.setInstallTime(row.getCell(27)==null?null:df.parse(row.getCell(27).getStringCellValue()));   		//��װʱ��
			entity.setManufactureTime(row.getCell(28)==null?null:df.parse(row.getCell(28).getStringCellValue()));   		//��������
			entity.setServiceIfe(row.getCell(29)==null?null:row.getCell(29).getStringCellValue());   		//����ʹ������
			entity.setYearlyTime1(row.getCell(30)==null?null:df.parse(row.getCell(30).getStringCellValue()));   		//�״μ��ʱ��
			entity.setState("����");
			entity.setDelflag("0");
			entity.setGatewayId(null);
			entity.setUserid(user);
			elevatorService.save(entity);
		}
		File fs = new File(fileName);
		if (fs.isFile() && fs.exists()) {
			fs.delete();
			System.out.println("ɾ�������ļ�" + fileName + "�ɹ���");
		} else {
			System.out.println("ɾ�������ļ�" + fileName + "ʧ�ܣ�");
		}
		 return	new ActionForward("/jsp/comm/close.jsp");
	}
	
}
