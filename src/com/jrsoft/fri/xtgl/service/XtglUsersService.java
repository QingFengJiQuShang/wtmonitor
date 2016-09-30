package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglUsers;

public interface XtglUsersService  extends BaseService< XtglUsers, String>{
	
	public XtglUsers getUserByPasswd(String loginName, String passwd) ;

}
