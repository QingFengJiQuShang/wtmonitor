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

import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglPropertyUnitService;
import com.jrsoft.fri.xtsz.action.Log;

public class XtglPropertyUnitAction extends DispatchAction  {
	private XtglPropertyUnitService propertyUnitService;

	public XtglPropertyUnitService getPropertyUnitService() {
		return propertyUnitService;
	}

	public void setPropertyUnitService(XtglPropertyUnitService propertyUnitService) {
		this.propertyUnitService = propertyUnitService;
	}
	/**
	 * 新增 物业单位信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglPropertyUnit elevator =XtglForm.getPropertyUnit();
		propertyUnitService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加物业单位，单位名称："+elevator.getName(), "1");
	    return	new ActionForward("/propertyUnitAction.do?method=query");
	}
	/**
	 * 查询 物业单位列表
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
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglPropertyUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from xtgl_property_unit de where  1=1 " ;
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
					sql+=" and name like '%"+name+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				if(phone!=null&&!phone.equals("")){
					phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
					sql+=" and phone like '%"+phone+"%'";
				}
				if(address!=null&&!address.equals("")){
					address=new String(address.getBytes("iso-8859-1"),"utf-8");
					sql+=" and address like '%"+address+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglPropertyUnit>();
				while(rs.next()){
					XtglPropertyUnit useUnit=new XtglPropertyUnit();
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
		
		
		 return	new ActionForward("/jsp/xtgl/propertyUnit/propertyUnitList.jsp");
		}
	
	/**
	 * 编辑 查看 物业单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglPropertyUnit list=propertyUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/propertyUnit/updatePropertyUnit.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/propertyUnit/dateilPropertyUnit.jsp");
		}
	}
	
	/**
	 * 修改物业单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglPropertyUnit unit =XtglForm.getPropertyUnit();
		XtglPropertyUnit useUnit=propertyUnitService.get(unit.getId());
		
		if(useUnit!=null){
			useUnit.setName(unit.getName());
			useUnit.setType(unit.getType());
			useUnit.setLiaisons(unit.getLiaisons());
			useUnit.setPhone(unit.getPhone());
			useUnit.setAddress(unit.getAddress());
			useUnit.setProvince(unit.getProvince());
			useUnit.setCity(unit.getCity());
			useUnit.setArea(unit.getArea());

			propertyUnitService.update(useUnit);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改物业单位，单位名称："+useUnit.getName(), "1");
		return	new ActionForward("/propertyUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除物业单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		XtglPropertyUnit useUnit=propertyUnitService.get(id);

		propertyUnitService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除物业单位，单位名称："+useUnit.getName(), "1");
		 return	new ActionForward("/propertyUnitAction.do?method=query");
	}
	/**
	 * 批量 删除 物业单位
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
				XtglPropertyUnit useUnit=propertyUnitService.get(Long.parseLong(arr[i]));
				propertyUnitService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除物业单位，单位名称："+useUnit.getName(), "1");
			}
		}
		 return	new ActionForward("/propertyUnitAction.do?method=query");
		
	}
	
	/**
	 * 导出 物业单位信息
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
		XtglPropertyUnit elevator=new XtglPropertyUnit();
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
			String fileName = "物业单位信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			propertyUnitService.export(filePath, elevator);

			// 下载excel
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
				System.out.println("删除单个文件" + fileName + "成功！");
			} else {
				System.out.println("删除单个文件" + fileName + "失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 *   选择 物业单位列表
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
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglPropertyUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from xtgl_property_unit de where  1=1 " ;
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
					sql+=" and name like '%"+name+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				if(phone!=null&&!phone.equals("")){
					phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
					sql+=" and phone like '%"+phone+"%'";
				}
				if(address!=null&&!address.equals("")){
					address=new String(address.getBytes("iso-8859-1"),"utf-8");
					sql+=" and address like '%"+address+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglPropertyUnit>();
				while(rs.next()){
					XtglPropertyUnit useUnit=new XtglPropertyUnit();
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
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
		
		 return	new ActionForward("/jsp/comm/selectPropertyUnitList.jsp");
		}
	/**
	 *   选择 物业单位列表
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
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String area=request.getParameter("area");
		String name=request.getParameter("name");
		String liaisons=request.getParameter("liaisons");
		String address=request.getParameter("address");
		String phone=request.getParameter("phone");
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglPropertyUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from xtgl_property_unit de where  1=1 " ;
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
					sql+=" and name like '%"+name+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					 liaisons=new String(liaisons.getBytes("iso-8859-1"),"utf-8");
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				if(phone!=null&&!phone.equals("")){
					phone=new String(phone.getBytes("iso-8859-1"),"utf-8");
					sql+=" and phone like '%"+phone+"%'";
				}
				if(address!=null&&!address.equals("")){
					address=new String(address.getBytes("iso-8859-1"),"utf-8");
					sql+=" and address like '%"+address+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglPropertyUnit>();
				while(rs.next()){
					XtglPropertyUnit useUnit=new XtglPropertyUnit();
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
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
		
		 return	new ActionForward("/jsp/comm/selectPropertyUnitList1.jsp");
		}
	/**
	 *   excel导入数据
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
	    Sheet sheet = workbook.getSheetAt(0);  //示意访问sheet  
		Row row = null;
		int totalRows = sheet.getPhysicalNumberOfRows();
		
		for(int r=1; r<totalRows; r++) 
		{
			XtglPropertyUnit entity =new XtglPropertyUnit();
			
			row = sheet.getRow(r);
			entity.setName(row.getCell(0).getStringCellValue());
			entity.setLiaisons(row.getCell(1).getStringCellValue());
			entity.setPhone(row.getCell(2).getStringCellValue());
			entity.setAddress(row.getCell(3).getStringCellValue());
			entity.setProvince(row.getCell(4).getStringCellValue());
			entity.setCity(row.getCell(5).getStringCellValue());
			entity.setArea(row.getCell(6).getStringCellValue());
			propertyUnitService.save(entity);
		}
		File fs = new File(fileName);
		if (fs.isFile() && fs.exists()) {
			fs.delete();
			System.out.println("删除导入文件" + fileName + "成功！");
		} else {
			System.out.println("删除导入文件" + fileName + "失败！");
		}
		 return	new ActionForward("/jsp/comm/close.jsp");
	}
}
