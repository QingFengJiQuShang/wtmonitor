package com.jrsoft.fri.xtgl.dao;


import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public interface XtglUsersDao extends BaseDao<XtglUsers, String>{
	
	public XtglUsers getUserByPasswd(String loginName, String passwd) ;

}
