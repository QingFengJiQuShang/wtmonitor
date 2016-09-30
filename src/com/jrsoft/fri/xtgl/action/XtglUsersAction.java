package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglUsersService;

public class XtglUsersAction extends DispatchAction {
	private XtglUsersService usersService;

	public XtglUsersService getUsersService() {
		return usersService;
	}

	public void setUsersService(XtglUsersService usersService) {
		this.usersService = usersService;
	}
	

}
