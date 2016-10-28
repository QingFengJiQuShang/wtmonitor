package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglMaintenanceUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;

public class XtglMaintenanceUnitServiceImpl    extends BaseServiceImpl<XtglMaintenanceUnit> implements XtglMaintenanceUnitService{
	private XtglMaintenanceUnitDao maintenanceUnitDao;

	public XtglMaintenanceUnitDao getMaintenanceUnitDao() {
		return maintenanceUnitDao;
	}

	public void setMaintenanceUnitDao(XtglMaintenanceUnitDao maintenanceUnitDao) {
		this.setBaseDao(maintenanceUnitDao);
		this.maintenanceUnitDao = maintenanceUnitDao;
	}

	@Override
	public void export(String filePath, XtglMaintenanceUnit gateway) {
		maintenanceUnitDao.export(filePath, gateway);
		
	}	
}
