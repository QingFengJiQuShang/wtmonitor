package com.jrsoft.fri.xtgl.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUnitService;
import com.jrsoft.fri.xtsz.action.Log;

public class XtglMaintenanceUnitAction  extends DispatchAction {
	private XtglMaintenanceUnitService maintenanceUnitService;

	public XtglMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}

	public void setMaintenanceUnitService(
			XtglMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}

	/**
	 * ���� ά����λ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglFrom=(XtglForm)form;
		XtglMaintenanceUnit elevator =XtglFrom.getMaintenanceUnit();
		maintenanceUnitService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "���ά����λ����λ���ƣ�"+elevator.getName(), "1");
	    return	new ActionForward("/maintenanceUnitAction.do?method=query");
	}
	/**
	 * ��ѯ ά����λ�б�
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
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<XtglMaintenanceUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_maintenance_unit de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(province!=null&&!province.equals("")){
					province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and province  ='" + province+ "'";
				}
				if(city!=null&&!city.equals("")){
					city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and city ='" + city+ "'";
				}
				if(area!=null&&!area.equals("")){
					area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
					sql+=" and area  ='" + area+ "'";
				}
				if(name!=null&&!name.equals("")){
					name=new String(name.getBytes("iso-8859-1"),"utf-8");
					sql+=" and name = '"+name+"'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
					sql+=" and liaisons  '"+liaisons+"'";
				}
				if(phone!=null&&!phone.equals("")){
					phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
					sql+=" and phone = '"+phone+"'";
				}
				if(address!=null&&!address.equals("")){
					address=new String(address.getBytes("iso-8859-1"),"utf-8");
					sql+=" and address like '%"+address+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMaintenanceUnit>();
				while(rs.next()){
					XtglMaintenanceUnit elevator=new XtglMaintenanceUnit();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setLiaisons(rs.getString("liaisons"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setAddress(rs.getString("address"));
					elevator.setCode(rs.getString("code"));
					elevator.setCorporation(rs.getString("corporation"));
					elevator.setProvince(rs.getString("province"));
					elevator.setCity(rs.getString("city"));
					elevator.setArea(rs.getString("area"));
					String sql2="select count(*)  from Xtgl_maintenance_users de where  1=1  and unit_Id = '"+rs.getLong("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					list.add(elevator);
					
				}
				request.setAttribute("province",province );
				request.setAttribute("city",city );
				request.setAttribute("area",area );
				request.setAttribute("name", name);
				request.setAttribute("address", address);
				request.setAttribute("liaisons", liaisons);
				request.setAttribute("phone", phone);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtgl/maintenanceUnit/maintenanceUnitList.jsp");
		}
	
	/**
	 * �༭ �鿴 ά����λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglMaintenanceUnit list=maintenanceUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/maintenanceUnit/updateMaintenanceUnit.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/maintenanceUnit/detailMaintenanceUnit.jsp");
		}
	}
	
	/**
	 * �޸�ά����λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglFrom=(XtglForm)form;
		XtglMaintenanceUnit unit =XtglFrom.getMaintenanceUnit();
		XtglMaintenanceUnit elevator=maintenanceUnitService.get(unit.getId());
		
		if(elevator!=null){
			elevator.setName(unit.getName());
			elevator.setLiaisons(unit.getLiaisons());
			elevator.setPhone(unit.getPhone());
			elevator.setAddress(unit.getAddress());
			elevator.setCode(unit.getCode());
			elevator.setCorporation(unit.getCorporation());
			elevator.setProvince(unit.getProvince());
			elevator.setCity(unit.getCity());
			elevator.setArea(unit.getArea());
			maintenanceUnitService.update(elevator);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸�ά����λ����λ���ƣ�"+elevator.getName(), "1");
		return	new ActionForward("/maintenanceUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * ɾ��ά����λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		XtglMaintenanceUnit elevator=maintenanceUnitService.get(id);

		maintenanceUnitService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ��ά����λ����λ���ƣ�"+elevator.getName(), "1");
		 return	new ActionForward("/maintenanceUnitAction.do?method=query");
	}
	/**
	 * ���� ɾ�� ά����λ
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
				XtglMaintenanceUnit elevator=maintenanceUnitService.get(Long.parseLong(arr[i]));
				maintenanceUnitService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ��ά����λ����λ���ƣ�"+elevator.getName(), "1");
			}
		}
		 return	new ActionForward("/maintenanceUnitAction.do?method=query");
		
	}
	
	/**
	 * ���� ά����λ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		XtglMaintenanceUnit elevator=new XtglMaintenanceUnit();
		if(province!=null&&!province.equals("")){
			province=new String(province.getBytes("ISO-8859-1"),"UTF-8");
			elevator.setProvince(province);
		}
		if(city!=null&&!city.equals("")){
			city=new String(city.getBytes("ISO-8859-1"),"UTF-8");
			elevator.setCity(city);
		}
		if(area!=null&&!area.equals("")){
			area=new String(area.getBytes("ISO-8859-1"),"UTF-8");
			elevator.setArea(area);
		}
		if(name!=null&&!name.equals("")){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
			elevator.setName(name);
		}
		if(liaisons!=null&&!liaisons.equals("")){
			 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
			 elevator.setLiaisons(liaisons);
		}
		if(phone!=null&&!phone.equals("")){
			phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
			elevator.setPhone(phone);
		}
		if(address!=null&&!address.equals("")){
			address=new String(address.getBytes("iso-8859-1"),"utf-8");
			elevator.setAddress(address);
		}
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "ά����λ��Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			maintenanceUnitService.export(filePath, elevator);

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
	 * ѡ�� ά����λ�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String name=request.getParameter("name");
		String code=request.getParameter("code");
		String liaisons=request.getParameter("liaisons");
		
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(code!=null){
			 code=new String(code.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(liaisons!=null){
			 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
		 }
		
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<XtglMaintenanceUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_maintenance_unit de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(code!=null&&!code.equals("")){
					sql+=" and code like '%"+code+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMaintenanceUnit>();
				while(rs.next()){
					XtglMaintenanceUnit elevator=new XtglMaintenanceUnit();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setLiaisons(rs.getString("liaisons"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setAddress(rs.getString("address"));
					elevator.setCode(rs.getString("code"));
					elevator.setCorporation(rs.getString("corporation"));
					
					String sql2="select count(*)  from Xtgl_maintenance_users de where  1=1  and unit_Id = '"+rs.getLong("id")+"'";
					int n=DBEntity.getInstance().queryDataCount(sql2);
					elevator.setNum(n);
					list.add(elevator);
					
				}
				
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("name", name);
				request.setAttribute("code", code);
				request.setAttribute("liaisons", liaisons);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/comm/selectMaintenanceUnitList.jsp");
		}
	/**
	 *   excel��������
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  exportIn(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String pathname = request.getRealPath("/")+"upload\\";
		Upload upload = new Upload();
		XtglForm f = (XtglForm)form;
		FormFile file = f.getTheFile();
		String fileName=upload.uploadFile(file, request, pathname);
		InputStream in = new FileInputStream(fileName);
	    Workbook workbook = WorkbookFactory.create(in);  
	    Sheet sheet = workbook.getSheetAt(0);  //ʾ�����sheet  
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		
		for(int r=1; r<totalRows; r++) 
		{
			XtglMaintenanceUnit entity =new XtglMaintenanceUnit();
			
			row = sheet.getRow(r);
			entity.setName(row.getCell(0).getStringCellValue());
			entity.setLiaisons(row.getCell(1).getStringCellValue());
			entity.setPhone(row.getCell(2).getStringCellValue());
			entity.setAddress(row.getCell(3).getStringCellValue());
			entity.setProvince(row.getCell(4).getStringCellValue());
			entity.setCity(row.getCell(5).getStringCellValue());
			entity.setArea(row.getCell(6).getStringCellValue());
			maintenanceUnitService.save(entity);
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
