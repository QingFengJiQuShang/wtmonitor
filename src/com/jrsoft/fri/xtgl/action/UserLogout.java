package com.jrsoft.fri.xtgl.action;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jrsoft.fri.xtgl.entity.XtglUsers;


public class UserLogout extends HttpServlet {
	private static final Log logger = LogFactory.getLog(UserLogout.class);
	private String systemName;
	private String welcomePage;
	@Override
	public void init(ServletConfig config) throws ServletException {
		systemName = config.getInitParameter("systemName");
		welcomePage = config.getInitParameter("welcomePage");
		if(systemName==null){
			systemName="\u4e1a\u52a1\u7cfb\u7edf";
		}
		if(welcomePage==null){
			welcomePage = config.getServletContext().getContextPath();
		}else if(!welcomePage.startsWith(config.getServletContext().getContextPath())){
			welcomePage = config.getServletContext().getContextPath()+welcomePage;
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		XtglUsers user =(XtglUsers)req.getSession().getAttribute("user");
		if(user!=null){
			Date date = new Date(System.currentTimeMillis());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			}
		req.getSession().invalidate();
		resp.sendRedirect(welcomePage);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
