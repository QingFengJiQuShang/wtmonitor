package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUsers;

public class DaxxMaintenanceUsersDaoImpl  extends BaseDaoImpl< DaxxMaintenanceUsers, String> implements  DaxxMaintenanceUsersDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxMaintenanceUsers.class;
	}


}
