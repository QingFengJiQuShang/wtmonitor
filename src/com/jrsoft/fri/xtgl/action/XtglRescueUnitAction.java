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

import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglRescueUnitService;

public class XtglRescueUnitAction  extends DispatchAction  {
	private XtglRescueUnitService rescueUnitService;

	public XtglRescueUnitService getRescueUnitService() {
		return rescueUnitService;
	}

	public void setRescueUnitService(XtglRescueUnitService rescueUnitService) {
		this.rescueUnitService = rescueUnitService;
	}
	
	/**
	 * 新增 救援单位信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglRescueUnit elevator =XtglForm.getRescueUnit();
		rescueUnitService.save(elevator);
	    return	new ActionForward("/useUnitAction.do?method=query");
	}
	/**
	 * 查询 救援单位列表
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
		
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		String hql=" where  1=1 " ;
		if(name!=null&&!name.equals("")){
			hql+=" and name like '%"+name+"%'";
		}
		if(liaisons!=null&&!liaisons.equals("")){
			hql+=" and liaisons like '%"+liaisons+"%'";
		}
		if(phone!=null&&!phone.equals("")){
			hql+=" and phone like '%"+phone+"%'";
		}
		hql+="order by id ";
		List<XtglRescueUnit> XtglRescueUnits=rescueUnitService.queryAll(hql);
		
		page.setPageSize(3);	//每页显示数
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCount(XtglRescueUnits.size());//总记录数
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<XtglRescueUnit> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_use_unit de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				if(liaisons!=null&&!liaisons.equals("")){
					sql+=" and liaisons like '%"+liaisons+"%'";
				}
				if(phone!=null&&!phone.equals("")){
					sql+=" and phone like '%"+phone+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglRescueUnit>();
				while(rs.next()){
					XtglRescueUnit useUnit=new XtglRescueUnit();
					useUnit.setId(rs.getLong("id"));
					useUnit.setName(rs.getString("name"));
					useUnit.setType(rs.getString("type"));
					useUnit.setLiaisons(rs.getString("liaisons"));
					useUnit.setPhone(rs.getString("phone"));
					useUnit.setAddress(rs.getString("address"));
					
					list.add(useUnit);
					
				}
				request.setAttribute("name", name);
				request.setAttribute("liaisons", liaisons);
				request.setAttribute("phone", phone);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/dagl/useUnit/useUnitList.jsp");
		}
	
	/**
	 * 编辑 查看 救援单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		XtglRescueUnit list=rescueUnitService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		return	new ActionForward("/jsp/dagl/useUnit/updateUseUnit.jsp");
	}
	
	/**
	 * 修改救援单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglForm=(XtglForm)form;
		XtglRescueUnit unit =XtglForm.getRescueUnit();
		XtglRescueUnit useUnit=rescueUnitService.get(unit.getId());
		
		if(useUnit!=null){
			useUnit.setName(unit.getName());
			useUnit.setType(unit.getType());
			useUnit.setLiaisons(unit.getLiaisons());
			useUnit.setPhone(unit.getPhone());
			useUnit.setAddress(unit.getAddress());
			
			rescueUnitService.update(useUnit);
		}
		return	new ActionForward("/useUnitAction.do?method=query");
	}
	
	
	
	
	/**
	 * 删除救援单位
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  delEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		Long id=Long.parseLong(request.getParameter("id"));
		rescueUnitService.delete(id);
		 return	new ActionForward("/useUnitAction.do?method=query");
	}
	/**
	 * 批量 删除 救援单位
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
				rescueUnitService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/useUnitAction.do?method=query");
		
	}
	
	/**
	 * 导出 救援单位信息
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
		XtglRescueUnit elevator=new XtglRescueUnit();
		elevator.setName(name);
		elevator.setLiaisons(liaisons);
		elevator.setPhone(phone);
		
		try {
			String dates = new SimpleDateFormat("yyyyMMddHHmmss")
					.format(new Date());
			String fileName = "救援单位信息" + dates + ".xls";
			String filePath = request.getRealPath("/")
					+ "excel\\" + fileName;
			// 生成excel文件
			rescueUnitService.export(filePath, elevator);

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


}
