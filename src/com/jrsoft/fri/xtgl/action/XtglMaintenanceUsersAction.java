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

import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglMaintenanceUsersService;
import com.jrsoft.fri.xtsz.action.Log;

import smart.sys.platform.dao.DBEntity;


public class XtglMaintenanceUsersAction  extends DispatchAction {
	private XtglMaintenanceUsersService maintenanceUsersService;

	public XtglMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}

	public void setMaintenanceUsersService(
			XtglMaintenanceUsersService maintenanceUsersService) {
		this.maintenanceUsersService = maintenanceUsersService;
	}
	

	/**
	 * 新增 维保人信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglMaintenanceUsers elevator =XtglForm.getMaintenanceUsers();
		maintenanceUsersService.save(elevator);
		
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加维保人员，人员姓名："+elevator.getName(), "1");
		
	    return	new ActionForward("/maintenanceUsersAction.do?method=query&unitId="+elevator.getUnitId().getId());
	}
	/**
	 * 查询 维保人列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String unitId=request.getParameter("unitId");
		String name=request.getParameter("name");
		String numbers=request.getParameter("numbers");
		String cardNumber=request.getParameter("cardNumber");
		if(unitId!=null){
			unitId=new String(unitId.getBytes("iso-8859-1"),"utf-8");
		 }
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		if(numbers!=null){
			numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		if(cardNumber!=null){
			cardNumber=new String(cardNumber.getBytes("iso-8859-1"),"utf-8");
		 }
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglMaintenanceUsers> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_maintenance_users de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and numbers like '%"+numbers+"%'";
				}
				if(unitId!=null&&!unitId.equals("")){
					sql+=" and unit_Id = '"+unitId+"'";
				}
				if(cardNumber!=null&&!cardNumber.equals("")){
					sql+=" and card_Number = '"+cardNumber+"'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMaintenanceUsers>();
				while(rs.next()){
					XtglMaintenanceUsers elevator=new XtglMaintenanceUsers();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setValidity(rs.getString("validity"));
					elevator.setCardNumber(rs.getString("card_Number"));
					list.add(elevator);
					
				}
				request.setAttribute("unitId", unitId);
				request.setAttribute("name", name);
				request.setAttribute("numbers", numbers);
				request.setAttribute("cardNumber", cardNumber);
				
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtgl/maintenanceUsers/maintenanceUsersList.jsp");
		}
	
	/**
	 * 编辑 查看 维保人
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglMaintenanceUsers list=maintenanceUsersService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/maintenanceUsers/updateMaintenanceUsers.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/maintenanceUsers/detailMaintenanceUsers.jsp");
		}
	}
	
	/**
	 * 修改区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglMaintenanceUsers unit =XtglForm.getMaintenanceUsers();
		XtglMaintenanceUsers elevator=maintenanceUsersService.get(unit.getId());
		
		if(elevator!=null){
			elevator.setName(unit.getName());
			elevator.setNumbers(unit.getNumbers());
			elevator.setPhone(unit.getPhone());
			elevator.setValidity(unit.getValidity());
			elevator.setCardNumber(unit.getCardNumber());
			maintenanceUsersService.update(elevator);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改维保人员，人员姓名："+elevator.getName(), "1");
		return	new ActionForward("/maintenanceUsersAction.do?method=query&unitId="+elevator.getUnitId().getId());
	}
	
	
	
	
	/**
	 * 删除区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		String unitId=request.getParameter("unitId");
		XtglMaintenanceUsers elevator=maintenanceUsersService.get(id);

		maintenanceUsersService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除维保人员，人员姓名："+elevator.getName(), "1");
        
		 return	new ActionForward("/maintenanceUsersAction.do?method=query&unitId="+unitId);
	}
	/**
	 * 批量 删除 区域
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  deleteEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String ids=request.getParameter("ids");
		String unitId=request.getParameter("unitId");
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				XtglMaintenanceUsers elevator=maintenanceUsersService.get(Long.parseLong(arr[i]));
				maintenanceUsersService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除维保人员，人员姓名："+elevator.getName(), "1");
			}
		}
		 return	new ActionForward("/maintenanceUsersAction.do?method=query&unitId="+unitId);
		
	}
	
	/**
	 * 导出 维保人信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  export(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String unitId=request.getParameter("unitId");
		String name=request.getParameter("name");
		String numbers=request.getParameter("numbers");
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		if(numbers!=null){
			numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		XtglMaintenanceUsers elevator=new XtglMaintenanceUsers();
		elevator.setName(name);
		elevator.setNumbers(numbers);
		XtglMaintenanceUnit maintenanceUnit=new XtglMaintenanceUnit();
		maintenanceUnit.setId(Long.parseLong(unitId));
		elevator.setUnitId(maintenanceUnit);
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "维保人信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			maintenanceUsersService.export(filePath, elevator);

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
	 * 选择 维保人列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query1(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String unitId=request.getParameter("unitId");
		String name=request.getParameter("name");
		String numbers=request.getParameter("numbers");
		String cardNumber=request.getParameter("cardNumber");
		
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		
		if(unitId!=null){
			unitId=new String(unitId.getBytes("iso-8859-1"),"utf-8");
		 }
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		if(numbers!=null){
			numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		if(cardNumber!=null){
			cardNumber=new String(cardNumber.getBytes("iso-8859-1"),"utf-8");
		 }
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglMaintenanceUsers> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_maintenance_users de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and numbers like '%"+numbers+"%'";
				}
				if(unitId!=null&&!unitId.equals("")){
					sql+=" and unit_Id = '"+unitId+"'";
				}
				if(cardNumber!=null&&!cardNumber.equals("")){
					sql+=" and card_Number = '"+cardNumber+"'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMaintenanceUsers>();
				while(rs.next()){
					XtglMaintenanceUsers elevator=new XtglMaintenanceUsers();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setValidity(rs.getString("validity"));
					elevator.setCardNumber(rs.getString("card_Number"));
					list.add(elevator);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("unitId", unitId);
				request.setAttribute("name", name);
				request.setAttribute("numbers", numbers);
				request.setAttribute("cardNumber", cardNumber);
				
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
				 return	new ActionForward("/jsp/comm/selectMaintenanceUsersList.jsp");
		}

	/**
	 * 选择 维保人列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query2(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String unitId=request.getParameter("unitId");
		String name=request.getParameter("name");
		String numbers=request.getParameter("numbers");
		String cardNumber=request.getParameter("cardNumber");
		
		String id=request.getParameter("id");
		String id1=request.getParameter("id1");
		String id2=request.getParameter("id2");
		String id3=request.getParameter("id3");
		if(unitId!=null){
			unitId=new String(unitId.getBytes("iso-8859-1"),"utf-8");
		 }
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		if(numbers!=null){
			numbers=new String(numbers.getBytes("iso-8859-1"),"utf-8");
		 }
		if(cardNumber!=null){
			cardNumber=new String(cardNumber.getBytes("iso-8859-1"),"utf-8");
		 }
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglMaintenanceUsers> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_maintenance_users de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(numbers!=null&&!numbers.equals("")){
					sql+=" and numbers like '%"+numbers+"%'";
				}
				if(unitId!=null&&!unitId.equals("")){
					sql+=" and unit_Id = '"+unitId+"'";
				}
				if(cardNumber!=null&&!cardNumber.equals("")){
					sql+=" and card_Number = '"+cardNumber+"'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMaintenanceUsers>();
				while(rs.next()){
					XtglMaintenanceUsers elevator=new XtglMaintenanceUsers();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setNumbers(rs.getString("numbers"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setValidity(rs.getString("validity"));
					elevator.setCardNumber(rs.getString("card_Number"));
					list.add(elevator);
					
				}
				request.setAttribute("id", id);
				request.setAttribute("id1", id1);
				request.setAttribute("id2", id2);
				request.setAttribute("id3", id3);
				request.setAttribute("unitId", unitId);
				request.setAttribute("name", name);
				request.setAttribute("numbers", numbers);
				request.setAttribute("cardNumber", cardNumber);
				
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
				 return	new ActionForward("/jsp/comm/selectMaintenanceUsersList.jsp");
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
			XtglMaintenanceUsers entity =new XtglMaintenanceUsers();
			
			row = sheet.getRow(r);
			entity.setName(row.getCell(0).getStringCellValue());
			entity.setPhone(row.getCell(2).getStringCellValue());
			entity.setNumbers(row.getCell(3).getStringCellValue());
			entity.setValidity(row.getCell(4).getStringCellValue());
			entity.setCardNumber(row.getCell(5).getStringCellValue());
			maintenanceUsersService.save(entity);
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
