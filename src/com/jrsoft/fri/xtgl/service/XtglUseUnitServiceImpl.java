package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglUseUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public class XtglUseUnitServiceImpl    extends BaseServiceImpl<XtglUseUnit> implements XtglUseUnitService{
	private XtglUseUnitDao useUnitDao;

	public XtglUseUnitDao getUseUnitDao() {
		return useUnitDao;
	}

	public void setUseUnitDao(XtglUseUnitDao useUnitDao) {
		this.setBaseDao(useUnitDao);
		this.useUnitDao = useUnitDao;
	}

	@Override
	public void export(String filePath, XtglUseUnit unit) {
		useUnitDao.export(filePath, unit);
		
	}


	

}
