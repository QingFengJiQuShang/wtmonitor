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

import com.jrsoft.fri.common.utils.StringUtils;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglUsersService;

public class XtglUsersAction extends DispatchAction {
	private XtglUsersService usersService;

	public XtglUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}
	
	/**
	 * 新增 系统用户信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtglForm XtglFrom=(XtglForm)form;
		XtglUsers elevator =XtglFrom.getUsers();
		if(elevator.getPassword()!=null&&!elevator.getPassword().equals("")){
			elevator.setPassword(StringUtils.encodeBase64(elevator.getPassword()));
		}
		
		if(elevator.getProvince().equals("全选择")){
			elevator.setProvince("");
		}
		if(elevator.getCity().equals("全选择")){
			elevator.setCity("");
		}
		usersService.save(elevator);
	    return	new ActionForward("/usersAction.do?method=query");
	}
	/**
	 * 查询 系统用户列表
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		String name=request.getParameter("name");
		String unit=request.getParameter("unit");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		
		if(name!=null){
			name=new String(name.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(unit!=null){
			 unit=new String(unit.getBytes("iso-8859-1"),"utf-8");
		 }
		 if(province!=null){
			 province=new String(province.getBytes("iso-8859-1"),"utf-8");
		 }else{
			 province="请选择";
		 }
		 if(city!=null){
			 city=new String(city.getBytes("iso-8859-1"),"utf-8");
		 }else{
				 city="请选择";
		 }
		String num=request.getParameter("num");   //当前页
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	
		
		List<XtglUsers> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询服务订单
				String sql="select de.*  from Xtgl_Users de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				
				if(unit!=null&&!unit.equals("")){
					sql+=" and unit like '%"+unit+"%'";
				}
				if(province!=null&&!province.equals("")&&!province.equals("请选择")){
					sql+=" and province like '%"+province+"%'";
				}
				if(city!=null&&!city.equals("")&&!city.equals("请选择")){
					sql+=" and city like '%"+city+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtglUsers>();
				while(rs.next()){
					XtglUsers elevator=new XtglUsers();
					elevator.setId(rs.getLong("id"));
					elevator.setName(rs.getString("name"));
					elevator.setLoginname(rs.getString("loginname"));
					elevator.setPhone(rs.getString("phone"));
					elevator.setUnit(rs.getString("unit"));
					elevator.setProvince(rs.getString("province"));
					elevator.setCity(rs.getString("city"));
					elevator.setArea(rs.getString("area"));
					list.add(elevator);	
				}
				request.setAttribute("name", name);
				request.setAttribute("unit", unit);
				request.setAttribute("province", province);
				request.setAttribute("city", city);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtgl/user/userList.jsp");
		}
	
	/**
	 * 编辑 查看 系统用户
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtglUsers list=usersService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtgl/user/update.jsp");
		}else{
			return	new ActionForward("/jsp/xtgl/user/detailUser.jsp");
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
		XtglForm XtglFrom=(XtglForm)form;
		XtglUsers unit =XtglFrom.getUsers();
		XtglUsers elevator=usersService.get(unit.getId());
		
		if(elevator!=null){
			elevator.setName(unit.getName());
			elevator.setPhone(unit.getPhone());
			elevator.setUnit(unit.getUnit());
			elevator.setProvince(unit.getProvince());
			elevator.setCity(unit.getCity());
			if(elevator.getPassword()!=null&&!elevator.getPassword().equals("")){
				elevator.setPassword(StringUtils.encodeBase64(elevator.getPassword()));
			}
			
			if(elevator.getProvince().equals("全选择")){
				elevator.setProvince("");
			}
			if(elevator.getCity().equals("全选择")){
				elevator.setCity("");
			}
			usersService.update(elevator);
		}
		return	new ActionForward("/usersAction.do?method=query");
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
		usersService.delete(id);
		 return	new ActionForward("/usersAction.do?method=query");
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
		if(ids!=null&&!ids.equals("")){
			String  arr []=ids.split(",");
			for(int i=0;i<arr.length;i++){
				usersService.delete(Long.parseLong(arr[i]));
			}
		}
		 return	new ActionForward("/usersAction.do?method=query");
		
	}
	
	

}
