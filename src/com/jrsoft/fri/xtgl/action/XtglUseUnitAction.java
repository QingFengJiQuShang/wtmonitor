package com.jrsoft.fri.xtgl.action;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.FileForm;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglUseUnitService;
import com.jrsoft.fri.xtsz.action.Log;

import smart.sys.platform.dao.DBEntity;

public class XtglUseUnitAction  extends DispatchAction  {
	private XtglUseUnitService useUnitService;

	public XtglUseUnitService getUseUnitService() {
		return useUnitService;
	}

	public void setUseUnitService(XtglUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}
	
	/**
	 * ���� ʹ�õ�λ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglUseUnit elevator =XtglForm.getUnit();
		useUnitService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "���ʹ�õ�λ����λ���ƣ�"+elevator.getName(), "1");
	    return	new ActionForward("/useUnitAction.do?method=query");
	}
	/**
	 * ��ѯ ʹ�õ�λ�б�
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String type=request.getParameter("type");
		
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
		
		List<XtglUseUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_use_unit de where  1=1 " ;
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
				list=new ArrayList<XtglUseUnit>();
				while(rs.next()){
					XtglUseUnit useUnit=new XtglUseUnit();
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
				request.setAttribute("name", name);
				request.setAttribute("liaisons", liaisons);
				request.setAttribute("type", type);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtgl/useUnit/useUnitList.jsp");
		}
	
	/**
	 * �༭ �鿴 ʹ�õ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglUseUnit list=useUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/useUnit/updateUseUnit.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/useUnit/detailUseUnit.jsp");
		}
	}
	
	/**
	 * �޸�ʹ�õ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglUseUnit unit =XtglForm.getUnit();
		XtglUseUnit useUnit=useUnitService.get(unit.getId());
		
		if(useUnit!=null){
			useUnit.setName(unit.getName());
			useUnit.setType(unit.getType());
			useUnit.setLiaisons(unit.getLiaisons());
			useUnit.setPhone(unit.getPhone());
			useUnit.setAddress(unit.getAddress());
			useUnit.setProvince(unit.getProvince());
			useUnit.setCity(unit.getCity());
			useUnit.setArea(unit.getArea());

			useUnitService.update(useUnit);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸�ʹ�õ�λ����λ���ƣ�"+useUnit.getName(), "1");
		return	new ActionForward("/useUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * ɾ��ʹ�õ�λ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		XtglUseUnit useUnit=useUnitService.get(id);

		useUnitService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ��ʹ�õ�λ����λ���ƣ�"+useUnit.getName(), "1");
		 return	new ActionForward("/useUnitAction.do?method=query");
	}
	/**
	 * ���� ɾ�� ʹ�õ�λ
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
				XtglUseUnit useUnit=useUnitService.get(Long.parseLong(arr[i]));
				useUnitService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ��ʹ�õ�λ����λ���ƣ�"+useUnit.getName(), "1");
			}
		}
		 return	new ActionForward("/useUnitAction.do?method=query");
		
	}
	
	/**
	 * ���� ʹ�õ�λ��Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {

		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String phone=request.getParameter("phone");
		
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(liaisons!=null){
			 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(phone!=null){
			 phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
		 }
		XtglUseUnit elevator=new XtglUseUnit();
		elevator.setName(name);
		elevator.setLiaisons(liaisons);
		elevator.setPhone(phone);
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "ʹ�õ�λ��Ϣ" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// ����excel�ļ�
			useUnitService.export(filePath, elevator);

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
	 *   ѡ�� ʹ�õ�λ�б�
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
		
		List<XtglUseUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_use_unit de where  1=1 " ;
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
				list=new ArrayList<XtglUseUnit>();
				while(rs.next()){
					XtglUseUnit useUnit=new XtglUseUnit();
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
		
		
		 return	new ActionForward("/jsp/comm/selectUseUnitList.jsp");
		}
	/**
	 *   excel��������
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public void  exportIn(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String url=request.getParameter("flie");
		String pathname = request.getRealPath("/")+"upload\\";
		Upload upload = new Upload();
		//upload.getExcelData(pathname);
		uploadFile(form, request, pathname);
	
	}
	/**
	 * �ϴ��ļ�
	 */
	public String uploadFile(ActionForm  form,HttpServletRequest request,String filePath){
		XtglForm f = (XtglForm)form;
		FormFile file = f.getTheFile();
		Date date = new Date();
		int i = 0;
		String type = file.getFileName();
		String path = "";
		while(i!=-1){
			i = type.indexOf(".");
			type = type.substring(i+1);//�ļ�����
		}
		String times = String.valueOf(date.getTime());
		String  fname = times + "." + type;
		try{
			InputStream streamIn = file.getInputStream();
			int ok=file.getFileSize();
			String strFee = null;
			if(ok>=1024*1024)
			{
				float ok1=(((float)ok)/1024f/1024f); 
				DecimalFormat myformat1 = new DecimalFormat("0.00");         
				strFee = myformat1.format(ok1)+"M";
				System.out.println(strFee+"M");
			}
			else if(ok>1024 && ok<=1024*1024)
			{
				double  ok2=((double)ok)/1024;
				DecimalFormat myformat2=new DecimalFormat("0.00");
				strFee = myformat2.format(ok2)+"kb";
				System.out.println(strFee+"kb");
			}
			else if(ok<1024)
			{
				strFee=String.valueOf(ok)+"byte";
				System.out.println(strFee);
			}
			System.out.println( streamIn.available()+"�ļ���Сbyte"+"   "+filePath);
			File uploadFile = new File(filePath);
			if (!uploadFile.exists() || uploadFile == null) {  
				uploadFile.mkdirs();
			}
			path = uploadFile.getPath() + "\\" + fname;
			OutputStream streamOut = new FileOutputStream(path);
			int bytesRead = 0;
			byte[] buffer = new byte[8192];
			while ((bytesRead = streamIn.read(buffer, 0, 8192)) != -1) {
				streamOut.write(buffer, 0, bytesRead);
			}
			streamOut.close();
			streamIn.close();
			file.destroy();  
			System.out.println("path==="+path);
		}catch(Exception e){
			e.printStackTrace();
		}
		return path;
	}
}
