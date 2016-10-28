package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglMaintenanceUsersDao;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;

public class XtglMaintenanceUsersServiceImpl    extends BaseServiceImpl<XtglMaintenanceUsers> implements XtglMaintenanceUsersService{
	private XtglMaintenanceUsersDao maintenanceUsersDao;

	public XtglMaintenanceUsersDao getMaintenanceUsersDao() {
		return maintenanceUsersDao;
	}

	public void setMaintenanceUsersDao(XtglMaintenanceUsersDao maintenanceUsersDao) {
		this.setBaseDao(maintenanceUsersDao);
		this.maintenanceUsersDao = maintenanceUsersDao;
	}

	@Override
	public void export(String filePath, XtglMaintenanceUsers users) {
		maintenanceUsersDao.export(filePath, users);
		
	}

	
}
