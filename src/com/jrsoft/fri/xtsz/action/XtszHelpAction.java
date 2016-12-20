package com.jrsoft.fri.xtsz.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import smart.sys.platform.dao.DBEntity;

import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtsz.entity.XtszHelp;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.XtszHelpService;

public class XtszHelpAction extends DispatchAction {
	private XtszHelpService helpService;

	public XtszHelpService getHelpService() {
		return helpService;
	}

	public void setHelpService(XtszHelpService helpService) {
		this.helpService = helpService;
	}
	
	/**
	 * ���� ������Ϣ
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		XtszHelp elevator =from.getHelp();
		
		helpService.save(elevator);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "���ϵͳ���������⣺"+elevator.getTitle(), "1");
	    return	new ActionForward("/helpAction.do?method=query");
	}
	
	
	/**
	 * ��ѯ ���ݰ���
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String title=request.getParameter("title");
		String num=request.getParameter("num");   //��ǰҳ
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//��ǰҳ��
		}else{
			page.setPageNum(0);//��ǰҳ��
		}
		
		List<XtszHelp> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//��ѯ��־
				String sql="select de.* from xtsz_help de  where  1=1 " ;
				if(title!=null&&!title.equals("")){
					sql+=" and de.title like '%"+title+"%'";
				}
				
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//�ܼ�¼��
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//��ҳ��	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtszHelp>();
				while(rs.next()){
					XtszHelp elevator=new XtszHelp();
					elevator.setId(rs.getLong("id"));
					elevator.setTitle(rs.getString("title"));
					elevator.setContent(rs.getString("content"));
					list.add(elevator);
					
				}
				
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
		
		 return	new ActionForward("/jsp/xtsz/help/helpList.jsp");
		}
	
	/**
	 * �༭ �鿴 ϵͳ����
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  findById(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String flag=request.getParameter("flag");
		XtszHelp list=helpService.get(Long.parseLong(id));
		request.setAttribute("list", list);
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/help/updateHelp.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/help/detailHelp.jsp");
		}
	}
	
	/**
	 * �޸ĵ���
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		
		XtszFrom from=(XtszFrom)form;
		XtszHelp elevator =from.getHelp();
		XtszHelp entity =helpService.get(elevator.getId());
		
		if(entity.getId()!=null){
			entity.setTitle(elevator.getTitle());
			entity.setContent(elevator.getContent());
			helpService.update(entity);
		}
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "�޸�ϵͳ���������⣺"+elevator.getTitle(), "1");
		return	new ActionForward("/helpAction.do?method=query");
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
		XtszHelp entity =helpService.get(id);
		helpService.delete(id);
		//���� ������־
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "ɾ��ϵͳ���������⣺"+entity.getTitle(), "1");
		 return	new ActionForward("/helpAction.do?method=query");
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
				XtszHelp entity =helpService.get(Long.parseLong(arr[i]));
				helpService.delete(Long.parseLong(arr[i]));
				//���� ������־
				XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
				Log log=new Log();
		        log.addLog(user.getName(), "ɾ��ϵͳ���������⣺"+entity.getTitle(), "1");
			}
		}
			return	new ActionForward("/helpAction.do?method=query");

	}
	

}
