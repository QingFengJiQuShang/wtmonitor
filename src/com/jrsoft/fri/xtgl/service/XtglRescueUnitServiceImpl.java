package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglRescueUnitDao;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;

public class XtglRescueUnitServiceImpl     extends BaseServiceImpl<XtglRescueUnit> implements XtglRescueUnitService{
	private XtglRescueUnitDao rescueUnitDao;

	

	public XtglRescueUnitDao getRescueUnitDao() {
		return rescueUnitDao;
	}



	public void setRescueUnitDao(XtglRescueUnitDao rescueUnitDao) {
		this.rescueUnitDao = rescueUnitDao;
	}



	@Override
	public void export(String filePath, XtglRescueUnit gateway) {
		rescueUnitDao.export(filePath, gateway);
		
	}	
}
