package com.jrsoft.fri.xtgl.action;

import smart.sys.platform.springUtils.SpringBeanUtil;
import com.jrsoft.fri.xtgl.entity.XtglUsers;
import com.jrsoft.fri.xtgl.service.XtglUsersService;

public class Users {
	private static XtglUsersService usersService = (XtglUsersService)SpringBeanUtil.getBean("usersService");
	
	public static XtglUsers login(String loginName,String password){
		XtglUsers user=usersService.getUserByPasswd(loginName, password);
		
		return user;
	}

}
