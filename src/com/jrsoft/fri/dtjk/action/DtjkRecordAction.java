package com.jrsoft.fri.dtjk.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.common.utils.JsonUtil;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;
import com.jrsoft.fri.dtjk.from.Control;
import com.jrsoft.fri.dtjk.service.DtjkElevatorService;
import com.jrsoft.fri.dtjk.service.DtjkGatewayService;
import com.jrsoft.fri.dtjk.service.DtjkRecordService;
import com.jrsoft.fri.tjfx.from.RegionCount;
import com.jrsoft.fri.xtgl.from.ExcelColumns;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;
import com.jrsoft.fri.xtsz.service.XtszDictionaryService;
import com.sun.org.apache.xerces.internal.impl.dtd.models.DFAContentModel;

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
	 * �鿴 ���ݼ�� 
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
		String hql=" where foundTime= ( select max(foundTime) from DtjkRecord where elevatorId='"+list.getRegisterid()+"') and elevatorId='"+list.getRegisterid()+"'  ";
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
		//����ˢ��ʱ��
		String hql1=" where flag='0'";
		List<XtszDictionary> dictionaries=dictionaryService.query(hql1);
		if(dictionaries.size()>0){
			request.setAttribute("dictionarie", dictionaries.get(0));
		}
		if(flag.equals("1")&&flag!=null)
			return	new ActionForward("/jsp/dtjk/monitor/left.jsp");		
		else if(flag.equals("2")&&flag!=null)
			return	new ActionForward("/jsp/dtjk/monitor/monitorDetail1.jsp");
		else
			return	new ActionForward("/jsp/dtjk/monitor/monitorDetail.jsp");
		
	}
	/**
	   * �鿴 ���ݼ�� 
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void findByMonitor1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String id=request.getParameter("id");
			DtjkElevator list=elevatorService.get(Long.parseLong(id));
			request.setAttribute("list", list);
			String hql=" where foundTime= ( select max(foundTime) from DtjkRecord where elevatorId='"+list.getRegisterid()+"')   and elevatorId='"+list.getRegisterid()+"'";
			//String hql="select t.* form () where elevatorId='"+list.getRegisterid()+"' order by t.foundTime desc    ";
			List<DtjkRecord> record=recordService.query(hql);
			String hql2=" where  1=1 and elevatorId='"+list.getRegisterid()+"' " ;
			List<DtjkGateway> gateways=gatewayService.queryAll(hql2);
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			//����ˢ��ʱ��
			String hql1=" where flag='0'";
			List<XtszDictionary> dictionaries=dictionaryService.query(hql1);
			JSONObject cell = new JSONObject();
			cell.put("id", list.getId());
			cell.put("state", list.getState());
			if(gateways.size()>0){
				cell.put("gateway", gateways.get(0));
			}
			if(record.size()>0){
				DtjkRecord records=record.get(0);
				records.setFoundTime1(df.format(records.getFoundTime()));
				records.setFoundTime(null);
				cell.put("records", records);
			}
			if(dictionaries.size()>0){
				cell.put("dictionarie", dictionaries.get(0));
			}
			JsonUtil.ajaxOutPutJson(response, cell);
	}
	/**
	 * �鿴 ��̨���ݼ�� 
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
				String hql=" where foundTime= ( select max(foundTime) from DtjkRecord where elevatorId='"+list.getRegisterid()+"')  and elevatorId='"+list.getRegisterid()+"' ";
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
		//����ˢ��ʱ��
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
	 * ��ѯ �����ϱ���¼�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String state=request.getParameter("state");
		String elevatorId=request.getParameter("elevatorId");
		String direction=request.getParameter("direction");
		String people=request.getParameter("people");
		String door=request.getParameter("door");
		String num=request.getParameter("num");   //��ǰҳ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(state!=null){
			state=new String(state.getBytes("iso-8859-1"),"utf-8");
		 }
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
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<DtjkRecord> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from dtjk_record de" +
						" left join dtjk_elevator e on e.registerid=de.elevator_id " +
						"   where  1=1 " ;
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.found_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.found_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_id = '"+elevatorId+"'";
				}
				if(direction!=null&&!direction.equals("")){
					sql+=" and de.direction = '"+direction+"'";
				}
				if(people!=null&&!people.equals("")){
					sql+=" and de.people = '"+people+"'";
				}
				if(door!=null&&!door.equals("")){
					sql+=" and de.door = '"+door+"'";
				}
				if(state!=null&&state.equals("����")){
					sql+=" and de.heartbeat!='21'  and de.maintenance_User_Id is null ";
				}else if(state!=null&&state.equals("����")){
					sql+=" and de.heartbeat='21' ";
				}else if(state!=null&&state.equals("ά��")){
					sql+=" and de.heartbeat='20' and  de.maintenance_User_Id  is not null";
				}
				sql+=" order by de.found_time desc  ";	
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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
					elevator.setFoundTime(df.parse((rs.getString("found_Time"))));
					elevator.setMaintenanceState(rs.getString("maintenance_State"));
					if(rs.getString("heartbeat")!=null&&rs.getString("heartbeat").equals("20")&&rs.getString("maintenance_User_Id")!=null){
						elevator.setState("ά��");
					}else if(rs.getString("heartbeat")!=null&&rs.getString("heartbeat").equals("21")){
						elevator.setState("����");
					}else{
						elevator.setState("����");
					}
					
					list.add(elevator);
					
				}
				
				request.setAttribute("elevatorId", elevatorId);
				request.setAttribute("direction",direction );
				request.setAttribute("people", people);
				request.setAttribute("door", door);
				request.setAttribute("state", state);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
				if(endtime!=null&&!endtime.equals("")){
					request.setAttribute("endtime", sdf.parse( endtime));
				}
				if(begintime!=null&&!begintime.equals("")){
					request.setAttribute("begintime", sdf.parse( begintime));
				}
		
		 return	new ActionForward("/jsp/dtjk/playback/playbackList.jsp");
		}
	
	/**
	 * �鿴 ������ϸ�ϱ���¼
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
	/**
	 * ���� ���ݼ�¼
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");
		String state=request.getParameter("state");
		String elevatorId=request.getParameter("elevatorId");
		String direction=request.getParameter("direction");
		String people=request.getParameter("people");
		String door=request.getParameter("door");
		String num=request.getParameter("num");   //��ǰҳ
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		if(state!=null){
			state=new String(state.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(direction!=null){
			 direction=new String(direction.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(people!=null){
			 people=new String(people.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(door!=null){
			 door=new String(door.getBytes("iso-8859-1"),"utf-8");
		 }

		
		
		List<DtjkRecord> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from dtjk_record de" +
						" left join dtjk_elevator e on e.registerid=de.elevator_id " +
						"   where  1=1 " ;
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.found_Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.found_Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(elevatorId!=null&&!elevatorId.equals("")){
					sql+=" and de.elevator_id = '"+elevatorId+"'";
				}
				if(direction!=null&&!direction.equals("")){
					sql+=" and de.direction = '"+direction+"'";
				}
				if(people!=null&&!people.equals("")){
					sql+=" and de.people = '"+people+"'";
				}
				if(door!=null&&!door.equals("")){
					sql+=" and de.door = '"+door+"'";
				}
				if(state!=null&&state.equals("����")){
					sql+=" and de.heartbeat!='21'  and de.maintenance_User_Id is null ";
				}else if(state!=null&&state.equals("����")){
					sql+=" and de.heartbeat='21' ";
				}else if(state!=null&&state.equals("ά��")){
					sql+=" and de.heartbeat='20' and  de.maintenance_User_Id  is not null";
				}
				sql+=" order by de.found_time desc  ";	
					PreparedStatement sta = conn.prepareStatement(sql);
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
					if(rs.getString("heartbeat")!=null&&rs.getString("heartbeat").equals("20")&&rs.getString("maintenance_User_Id")!=null){
						elevator.setState("ά��");
					}else if(rs.getString("heartbeat")!=null&&rs.getString("heartbeat").equals("21")){
						elevator.setState("����");
					}else{
						elevator.setState("����");
					}
					list.add(elevator);
					
				}
				
		try {
		String dates = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		String fileName = "�����ϱ���¼" + dates + ".xls";
		String filePath = request.getRealPath("/")
		+ "excel\\" + fileName;
				try {
					
			// ����excel�ļ�
					System.out.println("========>>" + filePath);
					//Excel ex = new Excel();
					HSSFWorkbook workbook = new HSSFWorkbook();
					// ��Excel�������н�һ����������Ϊȱʡֵ
					HSSFSheet sheet = null;
					HSSFRow row = null;
					HSSFCell cell = null;
					sheet = workbook.createSheet("�ϱ���¼");
					HSSFCellStyle style = workbook.createCellStyle(); // ��ʽ����
					style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// ��ֱ
					style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// ˮƽ
					/**
					 * ��������
					 */
					HSSFFont f = workbook.createFont();
					f.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// ����Ӵ�
					style.setFont(f);		
					String[] top_arraydis = null;
					
					if (1 == 1) {
						top_arraydis = ExcelColumns.record;
		
						row = sheet.createRow(0);// ����һ��
		
						for (int c = 0; c < top_arraydis.length; c++) {
							cell = row.createCell((short) c);// ������ �ֶ� 
							cell.setCellValue("����");
							cell.setCellStyle(style);
							cell.setCellValue(top_arraydis[c]);
						}
						int j = 0;
						for (int i = 0; i < list.size(); i++) {
							    row = sheet.createRow(i+1);
							    DtjkRecord	e = (DtjkRecord) list.get(i);
								
								cell = row.createCell(j);// ������ �ֶ�
								cell.setCellValue(e.getElevatorId());   
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getGatewayDate());  
		
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getGatewayTime());  
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getDirection());   //�����ͺ�
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getFloor());   
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getPeople());  
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getDoor());  
								
								cell = row.createCell(++j);// ������ �ֶ�
								cell.setCellValue(e.getState());  
								
								
								j = 0;
							}
					}
		
					// �½�һ����ļ���
					
					FileOutputStream fOut = new FileOutputStream(filePath);
		//			System.out.println(fOut + "-----");
					// ����Ӧ��Excel ����������
					workbook.write(fOut);
					fOut.flush();
					// �����������ر��ļ�
					fOut.close();
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("������ xlCreate() : " + e);
				}
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


		}
	
}
