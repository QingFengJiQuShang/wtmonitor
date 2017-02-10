package com.jrsoft.fri.xtsz.action;

import java.io.IOException;
import java.io.PrintWriter;
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

import com.jrsoft.fri.common.utils.StringUtils;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.from.Page;
import com.jrsoft.fri.xtgl.from.XtglForm;
import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.jrsoft.fri.xtsz.entity.XtszMessage;
import com.jrsoft.fri.xtsz.from.XtszFrom;
import com.jrsoft.fri.xtsz.service.XtszMessageService;

public class XtszMessageAction  extends DispatchAction {
	private XtszMessageService messageService;

	public XtszMessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(XtszMessageService messageService) {
		this.messageService = messageService;
	}
	
	/**
	 * 新增 短信信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  addEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		XtszMessage elevator =from.getMessage();
		messageService.save(elevator);
		//生成 操作日志
		XtglUsers user =(XtglUsers)request.getSession().getAttribute("user");
		Log log=new Log();
        log.addLog(user.getName(), "添加了一条短信，手机号："+elevator.getPhone(), "1");
	    return	new ActionForward("/messageAction.do?method=query&flag=0");
	}
	/**
	 * 查询 操作日志
	 * @param request
	 * @param response
	 * @param region
	 * @return
	 * @throws Exception
	 */
	public ActionForward  query(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
	throws Exception {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String flag=request.getParameter("flag");
		String phone=request.getParameter("phone");
		String begintime=request.getParameter("begintime");
		String endtime=request.getParameter("endtime");

		String num=request.getParameter("num");   //当前页
		Page  page=new Page();
		
		if(num!=null&&!num.equals("")){
			page.setPageNum(Integer.parseInt(num));//当前页数
		}else{
			page.setPageNum(0);//当前页数
		}
		
		List<XtszMessage> list=null;
		Connection conn=DBEntity.getInstance().getConnection();
				
				//查询日志
				String sql="select de.* from Xtsz_Message de  where  1=1 " ;
				if(phone!=null&&!phone.equals("")){
					sql+=" and de.phone  ='" + phone+ "'";
				}
				if(begintime!=null&&!begintime.equals("")){
					sql+=" and de.Time  >=to_date('" + begintime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(endtime!=null&&!endtime.equals("")){
					sql+=" and de.Time  <=to_date('" + endtime+ "','yyyy-MM-dd hh24:mi:ss')";
				}
				if(flag.equals("0")){
					sql+=" and de.state  ='未发送' ";
				}else{
					sql+=" and de.state  ='已发送' ";
				}
				
				sql+=" order by de.id desc";	
				String sql1="select * from ( select a.*,rownum rn from ("+sql+") a where rownum<="+page.getPageSize() * (page.getPageNum() +1)+") where rn>="+(page.getPageSize() * page.getPageNum()+1);
				int siz=	DBEntity.getInstance().queryCount(sql);
				page.setCount(siz);//总记录数
				page.setCountSize(page.getCount()%page.getPageSize()==0?page.getCount()/page.getPageSize():page.getCount()/page.getPageSize()+1);	//总页数	

				PreparedStatement sta = conn.prepareStatement(sql1);
				ResultSet rs = sta.executeQuery();
				list=new ArrayList<XtszMessage>();
				while(rs.next()){
					XtszMessage elevator=new XtszMessage();
					elevator.setId(rs.getLong("id"));
					elevator.setTime(rs.getString("Time")==null?null:df.parse(rs.getString("Time")));
					elevator.setPhone(rs.getString("phone"));
					elevator.setState(rs.getString("state"));
					elevator.setContent(rs.getString("content"));
					list.add(elevator);
					
				}
				if(endtime!=null){
					request.setAttribute("endtime", df.parse( endtime));
				}
				if(begintime!=null){
					request.setAttribute("begintime", df.parse( begintime));
				}
				
				request.setAttribute("flag", flag);
				request.setAttribute("phone", phone);
				request.setAttribute("page", page);
				request.setAttribute("list", list);
		
				if(flag.equals("0")){
					return	new ActionForward("/jsp/xtsz/message/messageList.jsp");
				}else{
					return	new ActionForward("/jsp/xtsz/message/messageLog.jsp");
				}
		 
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
		XtszMessage list=messageService.get(Long.parseLong(id));
		
		
		request.setAttribute("list", list);
		
		if(flag.equals("1")){
			return	new ActionForward("/jsp/xtsz/message/updateMessage.jsp");
		}else{
			return	new ActionForward("/jsp/xtsz/message/detailMessage.jsp");
		}
		
	}
	
	

	/**
	 * 编辑 修改短信内容
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward  updateEntity(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		XtszFrom from=(XtszFrom)form;
		XtszMessage message =from.getMessage();
		XtszMessage list=messageService.get(message.getId());
		if(list!=null){
			list.setPhone(message.getPhone());
			list.setContent(message.getContent());
			messageService.update(list);
		}
		
		return	new ActionForward("/messageAction.do?method=query&flag=0");
	}
	

	/**
	 * 编辑 发送短信
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void  send(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response )
			throws Exception {
		String id=request.getParameter("id");
		String str=Message.sendMessage(Long.parseLong(id));
		
		PrintWriter out;
		try {
				out = response.getWriter();
				out.write(str); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
