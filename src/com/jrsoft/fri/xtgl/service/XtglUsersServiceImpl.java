package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglUsersDao;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public class XtglUsersServiceImpl  extends BaseServiceImpl<XtglUsers, String> implements XtglUsersService{
	private XtglUsersDao usersDao;

	public XtglUsersDao getUsersDao() {
		return usersDao;
	}

	public void setUsersDao(XtglUsersDao usersDao) {
		this.setBaseDao(usersDao);
		this.usersDao = usersDao;
	}

	@Override
	public XtglUsers getUserByPasswd(String loginName, String passwd) {
		
		return usersDao.getUserByPasswd(loginName, passwd);
	}
	
}
