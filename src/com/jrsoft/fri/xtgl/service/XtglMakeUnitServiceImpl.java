package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglMakeUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;

public class XtglMakeUnitServiceImpl    extends BaseServiceImpl<XtglMakeUnit> implements XtglMakeUnitService{
	private XtglMakeUnitDao makeUnitDao;

	public XtglMakeUnitDao getMakeUnitDao() {
		return makeUnitDao;
	}

	public void setMakeUnitDao(XtglMakeUnitDao makeUnitDao) {
		this.setBaseDao(makeUnitDao);
		this.makeUnitDao = makeUnitDao;
	}

	
	@Override
	public void export(String filePath, XtglMakeUnit unit) {
		makeUnitDao.export(filePath, unit);
		
	}



}
