package com.jrsoft.fri.common.utils;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.jrsoft.fri.xtgl.entity.XtglUsers;


public class OnlineUserListener implements HttpSessionListener {
	
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		System.out.println("kaishi---------------------------");
		se.getSession().setMaxInactiveInterval(-1);//单位是苗
		//se.getSession().setMaxInactiveInterval(1200);//单位是苗
		//session.setMaxInactiveInterval(int i) 
		
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("要销毁session了，要删除application中的值了。。。");
		// TODO Auto-generated method stub
		XtglUsers user =  (XtglUsers)se.getSession().getAttribute("user");

	   
		//得到application
		ServletContext application = se.getSession().getServletContext();
		Map<String, String> appMap =(Map<String, String>)application.getAttribute("app");
		if(user!=null&&appMap!=null){
				appMap.remove(user.getLoginname()+user.getPassword()+"userkey");
				//appMap.remove(s);
		}
		application.setAttribute("app", appMap);
	}
}
