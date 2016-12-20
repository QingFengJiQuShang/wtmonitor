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
	   * ��֤ �жϵ�¼��Ψһ
	   * @param request
	   * @param respons
	   * @param productList
	   * @param productSeries
	   * @param productImageList
	 * @throws Exception 
	   */
		public void onlyUser(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
			
			String loginname=request.getParameter("loginname");   //�û���
				//������ϵ�˱��
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
	 * ���� ϵͳ�û���Ϣ
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
		
		if(elevator.getProvince().equals("ȫѡ��")){
			elevator.setProvince("");
		}
		if(elevator.getCity().equals("ȫѡ��")){
			elevator.setCity("");
		}
		usersService.save(elevator);
		//����Ȩ��
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
		
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "���ϵͳ�û����û����ƣ�"+elevator.getName(), "1");
		
	    return	new ActionForward("/usersAction.do?method=query");
	}
	/**
	 * ��ѯ ϵͳ�û��б�
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
			 province="��ѡ��";
		 }
		 if(city!=null){
			 city=new String(city.getBytes("iso-8859-1"),"utf-8");
		 }else{
				 city="��ѡ��";
		 }
		String num=request.getParameter("num");   //��ǰҳ
		

		Page  page=new Page();
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<XtglUsers> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ���񶩵�
				String sql="select de.*  from Xtgl_Users de where  1=1 " ;
				if(name!=null&&!name.equals("")){
					sql+=" and name like '%"+name+"%'";
				}
				
				if(unit!=null&&!unit.equals("")){
					sql+=" and unit like '%"+unit+"%'";
				}
				if(province!=null&&!province.equals("")&&!province.equals("��ѡ��")){
					sql+=" and province like '%"+province+"%'";
				}
				if(city!=null&&!city.equals("")&&!city.equals("��ѡ��")){
					sql+=" and city like '%"+city+"%'";
				}
				sql+=" order by id";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

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
	 * �༭ �鿴 ϵͳ�û�
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
	 * �޸�����
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
			
			if(elevator.getProvince().equals("ȫѡ��")){
				elevator.setProvince("");
			}
			if(elevator.getCity().equals("ȫѡ��")){
				elevator.setCity("");
			}
			usersService.update(elevator);
			
			//�޸��û�Ȩ��
			//ɾ�����е�Ȩ��
			String sql="delete xtgl_authority where ( flag='0' or flag is null ) and  users_Id='"+elevator.getId()+"' ";
			DBEntity.getInstance().executeSql(sql);
			//���±���Ȩ��
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
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸�ϵͳ�û����û����ƣ�"+elevator.getName(), "1");
		return	new ActionForward("/usersAction.do?method=query");
	}
	
	/**
	 * �༭ �鿴 ����Ȩ��
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
	 * �޸Ķ���Ȩ��
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateMessage (ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
			//�޸��û�Ȩ��
			//ɾ�����е�Ȩ��
			String sql="delete xtgl_authority where  flag='1'   ";
			DBEntity.getInstance().executeSql(sql);
			//���±���Ȩ��
			String [] authority=request.getParameterValues("authority");
			if(authority!=null){			
				for(String key:authority){
					XtglAuthority xtglAuthority=new XtglAuthority();
					xtglAuthority.setKey(key);
					xtglAuthority.setFlag("1");
					authorityService.save(xtglAuthority);
				}
			}
			//���� ������־
			XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
			Log log=new Log();
	        log.addLog(user.getName(), "�޸Ķ���Ȩ��", "1");
			return	new ActionForward("/usersAction.do?method=findByMessage");
	}
	
	
	/**
	 * ɾ������
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
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ��ϵͳ�û����û����ƣ�"+elevator.getName(), "1");
		 return	new ActionForward("/usersAction.do?method=query");
	}
	/**
	 * ���� ɾ�� ����
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
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ��ϵͳ�û����û����ƣ�"+elevator.getName(), "1");
			}
		}
		 return	new ActionForward("/usersAction.do?method=query");
		
	}
	
	

}
