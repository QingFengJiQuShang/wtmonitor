package com.jrsoft.fri.xtgl.action;

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

import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglMakeUnitService;
import com.jrsoft.fri.xtsz.action.Log;

public class XtglMakeUnitAction extends DispatchAction  {
	private XtglMakeUnitService makeUnitService;

	public XtglMakeUnitService getMakeUnitService() {
		return makeUnitService;
	}

	public void setMakeUnitService(XtglMakeUnitService makeUnitService) {
		this.makeUnitService = makeUnitService;
	}
	/**
	 * 新增 制造单位信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglMakeUnit elevator =XtglForm.getMakeUnit();
		makeUnitService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加制造单位，单位名称："+elevator.getName(), "1");
	    return	new ActionForward("/makeUnitAction.do?method=query");
	}
	/**
	 * 查询 制造单位列表
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
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglMakeUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_Make_Unit de where  1=1 " ;
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
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMakeUnit>();
				while(rs.next()){
					XtglMakeUnit useUnit=new XtglMakeUnit();
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
		
		
		 return	new ActionForward("/jsp/xtgl/makeUnit/makeUnitList.jsp");
		}
	
	/**
	 * 编辑 查看 制造单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglMakeUnit list=makeUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/makeUnit/updateMakeUnit.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/makeUnit/detailMakeUnit.jsp");
		}
	}
	
	/**
	 * 修改制造单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglMakeUnit unit =XtglForm.getMakeUnit();
		XtglMakeUnit useUnit=makeUnitService.get(unit.getId());
		
		if(useUnit!=null){
			useUnit.setName(unit.getName());
			useUnit.setType(unit.getType());
			useUnit.setLiaisons(unit.getLiaisons());
			useUnit.setPhone(unit.getPhone());
			useUnit.setAddress(unit.getAddress());
			useUnit.setProvince(unit.getProvince());
			useUnit.setCity(unit.getCity());
			useUnit.setArea(unit.getArea());

			makeUnitService.update(useUnit);
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改制造单位，单位名称："+useUnit.getName(), "1");
		return	new ActionForward("/makeUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除制造单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		XtglMakeUnit useUnit=makeUnitService.get(id);

		makeUnitService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除制造单位，单位名称："+useUnit.getName(), "1");
		 return	new ActionForward("/makeUnitAction.do?method=query");
	}
	/**
	 * 批量 删除 制造单位
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
				XtglMakeUnit useUnit=makeUnitService.get(Long.parseLong(arr[i]));
				makeUnitService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除制造单位，单位名称："+useUnit.getName(), "1");
			}
		}
		 return	new ActionForward("/makeUnitAction.do?method=query");
		
	}
	
	/**
	 * 导出 制造单位信息
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
		XtglMakeUnit elevator=new XtglMakeUnit();
		elevator.setName(name);
		elevator.setLiaisons(liaisons);
		elevator.setPhone(phone);
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "制造单位信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			makeUnitService.export(filePath, elevator);

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
	 *   选择 制造单位列表
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
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtglMakeUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
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
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglMakeUnit>();
				while(rs.next()){
					XtglMakeUnit useUnit=new XtglMakeUnit();
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

}
