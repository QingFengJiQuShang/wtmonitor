package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxMaintenanceUsersDao;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUsers;

public class DaxxMaintenanceUsersServiceImpl    extends BaseServiceImpl<DaxxMaintenanceUsers> implements DaxxMaintenanceUsersService{
	private DaxxMaintenanceUsersDao maintenanceUsersDao;

	public DaxxMaintenanceUsersDao getMaintenanceUsersDao() {
		return maintenanceUsersDao;
	}

	public void setMaintenanceUsersDao(DaxxMaintenanceUsersDao maintenanceUsersDao) {
		this.setBaseDao(maintenanceUsersDao);
		this.maintenanceUsersDao = maintenanceUsersDao;
	}

	
}
