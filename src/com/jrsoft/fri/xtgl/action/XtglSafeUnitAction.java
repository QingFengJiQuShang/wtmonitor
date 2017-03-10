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

import com.jrsoft.fri.xtgl.entity.XtglSafeUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglSafeUnitService;
import com.jrsoft.fri.xtsz.action.Log;

public class XtglSafeUnitAction extends DispatchAction  {
	private XtglSafeUnitService safeUnitService;

	public XtglSafeUnitService getSafeUnitService() {
		return safeUnitService;
	}

	public void setSafeUnitService(XtglSafeUnitService safeUnitService) {
		this.safeUnitService = safeUnitService;
	}
	
	/**
	 * ���� ���յ�λ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglSafeUnit elevator =XtglForm.getSafeUnit();
		safeUnitService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "��ӱ��յ�λ����λ���ƣ�"+elevator.getName(), "1");
	    return	new ActionForward("/safeUnitAction.do?method=query");
	}
	/**
	 * ��ѯ ���յ�λ�б�
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
		
		List<XtglSafeUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_Safe_Unit de where  1=1 " ;
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
				list=new ArrayList<XtglSafeUnit>();
				while(rs.next()){
					XtglSafeUnit useUnit=new XtglSafeUnit();
					useUnit.setId(rs.getLong("id"));
					useUnit.setName(rs.getString("name"));
					useUnit.setType(rs.getString("type"));
					useUnit.setLiaisons(rs.getString("liaisons"));
					useUnit.setPhone(rs.getString("phone"));
					useUnit.setAddress(rs.getString("address"));
					useUnit.setProvince(rs.getString("province"));
					useUnit.setCity(rs.getString("city"));
					useUnit.setArea(rs.getString("area"));
					list.add(useUnit);
					
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
		
		
		 return	new ActionForward("/jsp/xtgl/safeUnit/safeUnitList.jsp");
		}
	
	/**
	 * �༭ �鿴 ���յ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglSafeUnit list=safeUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/propertyUnit/updateSafeUnit.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/propertyUnit/detailSafeUnit.jsp");
		}
	}
	
	/**
	 * �޸ı��յ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglSafeUnit unit =XtglForm.getSafeUnit();
		XtglSafeUnit useUnit=safeUnitService.get(unit.getId());
		
		if(useUnit!=null){
			useUnit.setName(unit.getName());
			useUnit.setType(unit.getType());
			useUnit.setLiaisons(unit.getLiaisons());
			useUnit.setPhone(unit.getPhone());
			useUnit.setAddress(unit.getAddress());
			useUnit.setProvince(unit.getProvince());
			useUnit.setCity(unit.getCity());
			useUnit.setArea(unit.getArea());

			safeUnitService.update(useUnit);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸ı��յ�λ����λ���ƣ�"+useUnit.getName(), "1");
		return	new ActionForward("/safeUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * ɾ�����յ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		XtglSafeUnit useUnit=safeUnitService.get(id);

		safeUnitService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ�����յ�λ����λ���ƣ�"+useUnit.getName(), "1");
		 return	new ActionForward("/safeUnitAction.do?method=query");
	}
	/**
	 * ���� ɾ�� ���յ�λ
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
				XtglSafeUnit useUnit=safeUnitService.get(Long.parseLong(arr[i]));
				safeUnitService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ�����յ�λ����λ���ƣ�"+useUnit.getName(), "1");
			}
		}
		 return	new ActionForward("/safeUnitAction.do?method=query");
		
	}
	
	/**
	 * ���� ���յ�λ��Ϣ
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
		XtglSafeUnit elevator=new XtglSafeUnit();
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
			String fileName = "���յ�λ��Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			safeUnitService.export(filePath, elevator);

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
	 *   ѡ�� ���յ�λ�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String type=request.getParameter("type");
		
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(liaisons!=null){
			 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(type!=null){
			 type=new String(type.getBytes("iso-8859-1"),"utf-8");
		 }
		
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<XtglSafeUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_Safe_Unit de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				if(type!=null&&!type.equals("")){
					sql+=" and type like '%"+type+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglSafeUnit>();
				while(rs.next()){
					XtglSafeUnit useUnit=new XtglSafeUnit();
					useUnit.setId(rs.getLong("id"));
					useUnit.setName(rs.getString("name"));
					useUnit.setType(rs.getString("type"));
					useUnit.setLiaisons(rs.getString("liaisons"));
					useUnit.setPhone(rs.getString("phone"));
					useUnit.setAddress(rs.getString("address"));
					
					list.add(useUnit);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("name", name);
				request.setAttribute("liaisons", liaisons);
				request.setAttribute("type", type);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/comm/selectSafeUnit.jsp");
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
			XtglSafeUnit entity =new XtglSafeUnit();
			
			row = sheet.getRow(r);
			entity.setName(row.getCell(0).getStringCellValue());
			entity.setLiaisons(row.getCell(1).getStringCellValue());
			entity.setPhone(row.getCell(2).getStringCellValue());
			entity.setAddress(row.getCell(3).getStringCellValue());
			entity.setProvince(row.getCell(4).getStringCellValue());
			entity.setCity(row.getCell(5).getStringCellValue());
			entity.setArea(row.getCell(6).getStringCellValue());
			safeUnitService.save(entity);
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
