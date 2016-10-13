package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxMaintenanceUnitDao;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUnit;

public class DaxxMaintenanceUnitServiceImpl    extends BaseServiceImpl<DaxxMaintenanceUnit> implements DaxxMaintenanceUnitService{
	private DaxxMaintenanceUnitDao maintenanceUnitDao;

	public DaxxMaintenanceUnitDao getMaintenanceUnitDao() {
		return maintenanceUnitDao;
	}

	public void setMaintenanceUnitDao(DaxxMaintenanceUnitDao maintenanceUnitDao) {
		this.setBaseDao(maintenanceUnitDao);
		this.maintenanceUnitDao = maintenanceUnitDao;
	}

	@Override
	public void export(String filePath, DaxxMaintenanceUnit gateway) {
		maintenanceUnitDao.export(filePath, gateway);
		
	}	
}
