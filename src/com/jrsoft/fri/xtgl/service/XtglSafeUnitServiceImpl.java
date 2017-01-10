package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglSafeUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglSafeUnit;

public class XtglSafeUnitServiceImpl    extends BaseServiceImpl<XtglSafeUnit> implements XtglSafeUnitService{
	private XtglSafeUnitDao safeUnitDao;

	public XtglSafeUnitDao getSafeUnitDao() {
		return safeUnitDao;
	}

	public void setSafeUnitDao(XtglSafeUnitDao safeUnitDao) {
		this.setBaseDao(safeUnitDao);
		this.safeUnitDao = safeUnitDao;
	}


	@Override
	public void export(String filePath, XtglSafeUnit unit) {
		safeUnitDao.export(filePath, unit);
		
	}


}
