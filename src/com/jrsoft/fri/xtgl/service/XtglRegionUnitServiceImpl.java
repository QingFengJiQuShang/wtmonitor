package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglRegionUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglRegionUnit;

public class XtglRegionUnitServiceImpl    extends BaseServiceImpl<XtglRegionUnit> implements XtglRegionUnitService{
	private XtglRegionUnitDao regionUnitDao;

	public XtglRegionUnitDao getRegionUnitDao() {
		return regionUnitDao;
	}

	public void setRegionUnitDao(XtglRegionUnitDao regionUnitDao) {
		this.setBaseDao(regionUnitDao);
		this.regionUnitDao = regionUnitDao;
	}



	@Override
	public void export(String filePath, XtglRegionUnit unit) {
		regionUnitDao.export(filePath, unit);
		
	}



}
