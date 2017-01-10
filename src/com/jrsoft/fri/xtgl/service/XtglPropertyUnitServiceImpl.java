package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglPropertyUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;

public class XtglPropertyUnitServiceImpl    extends BaseServiceImpl<XtglPropertyUnit> implements XtglPropertyUnitService{
	private XtglPropertyUnitDao propertyUnitDao;

	public XtglPropertyUnitDao getPropertyUnitDao() {
		return propertyUnitDao;
	}

	public void setPropertyUnitDao(XtglPropertyUnitDao propertyUnitDao) {
		this.setBaseDao(propertyUnitDao);
		this.propertyUnitDao = propertyUnitDao;
	}


	@Override
	public void export(String filePath, XtglPropertyUnit unit) {
		propertyUnitDao.export(filePath, unit);
		
	}



}
