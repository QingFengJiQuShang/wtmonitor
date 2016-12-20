package com.jrsoft.fri.xtgl.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.record.chart.BarRecord;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;
import com.jrsoft.fri.common.utils.StringUtils;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtgl.service.XtglAuthorityService;
import com.jrsoft.fri.xtgl.service.XtglUsersService;
import com.jrsoft.fri.xtsz.action.Log;

public class XtglUsersAction extends DispatchAction {
	private XtglUsersService usersService;
	private XtglAuthorityService authorityService;

	public XtglUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}
	
	public XtglAuthorityService getAuthorityService() {
		return authorityService;
	}

	public void setAuthorityService(XtglAuthorityService authorityService) {
		this.authorityService = authorityService;
	}

	/**
	   * 验证 判断登录名唯一
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void onlyUser(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String loginname=request.getParameter("loginname");   //用户名
				//生成联系人编号
				String hql=" where 1=1 and   loginname = '"+loginname+"'  order by id asc ";
				List<XtglUsers> content=usersService.query(hql);	
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
		//保存权限
		String [] authority=request.getParameterValues("authority");
		if(authority!=null){
			for(String key:authority){
				XtglAuthority xtglAuthority=new XtglAuthority();
				xtglAuthority.setUsersId(elevator.getId());
				xtglAuthority.setKey(key);
				xtglAuthority.setFlag("0");
				authorityService.save(xtglAuthority);
			}
		}
		
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加系统用户，用户名称："+elevator.getName(), "1");
		
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
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

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
		String password=StringUtils.decodeBase64(list.getPassword());
		list.setPassword(password);
		request.setAttribute("list", list);
		
		String hql=" where 1=1 and ( flag='0' or flag is null ) and  usersId='"+id+"' ";
		List<XtglAuthority> authority=authorityService.query(hql);
		request.setAttribute("authority", authority);
		
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
			if(unit.getPassword()!=null&&!unit.getPassword().equals("")){
				elevator.setPassword(StringUtils.encodeBase64(unit.getPassword()));
			}
			
			if(elevator.getProvince().equals("全选择")){
				elevator.setProvince("");
			}
			if(elevator.getCity().equals("全选择")){
				elevator.setCity("");
			}
			usersService.update(elevator);
			
			//修改用户权限
			//删除已有的权限
			String sql="delete xtgl_authority where ( flag='0' or flag is null ) and  users_Id='"+elevator.getId()+"' ";
			DBEntity.getInstance().executeSql(sql);
			//重新保存权限
			String [] authority=request.getParameterValues("authority");
			if(authority!=null){			
				for(String key:authority){
					XtglAuthority xtglAuthority=new XtglAuthority();
					xtglAuthority.setUsersId(elevator.getId());
					xtglAuthority.setKey(key);
					xtglAuthority.setFlag("0");
					authorityService.save(xtglAuthority);
				}
			}
		}
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "修改系统用户，用户名称："+elevator.getName(), "1");
		return	new ActionForward("/usersAction.do?method=query");
	}
	
	/**
	 * 编辑 查看 短信权限
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findByMessage(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		String hql=" where 1=1 and  flag='1'  ";
		List<XtglAuthority> authority=authorityService.query(hql);
		request.setAttribute("authority", authority);
		
		
		return	new ActionForward("/jsp/xtsz/message/jurisdiction.jsp");
	}
	/**
	 * 修改短信权限
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateMessage (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
			//修改用户权限
			//删除已有的权限
			String sql="delete xtgl_authority where  flag='1'   ";
			DBEntity.getInstance().executeSql(sql);
			//重新保存权限
			String [] authority=request.getParameterValues("authority");
			if(authority!=null){			
				for(String key:authority){
					XtglAuthority xtglAuthority=new XtglAuthority();
					xtglAuthority.setKey(key);
					xtglAuthority.setFlag("1");
					authorityService.save(xtglAuthority);
				}
			}
			//生成 操作日志
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			Log log=new Log();
	        log.addLog(user.getName(), "修改短信权限", "1");
			return	new ActionForward("/usersAction.do?method=findByMessage");
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
		XtglUsers elevator=usersService.get(id);
		usersService.delete(id);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "删除系统用户，用户名称："+elevator.getName(), "1");
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
				XtglUsers elevator=usersService.get(Long.parseLong(arr[i]));
				usersService.delete(Long.parseLong(arr[i]));
				//生成 操作日志
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "删除系统用户，用户名称："+elevator.getName(), "1");
			}
		}
		 return	new ActionForward("/usersAction.do?method=query");
		
	}
	
	

}
