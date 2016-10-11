package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxUseUnitDao;
import com.jrsoft.fri.dagl.entity.DaxxUseUnit;

public class DaxxUseUnitServiceImpl    extends BaseServiceImpl<DaxxUseUnit> implements DaxxUseUnitService{
	private DaxxUseUnitDao useUnitDao;

	public DaxxUseUnitDao getUseUnitDao() {
		return useUnitDao;
	}

	public void setUseUnitDao(DaxxUseUnitDao useUnitDao) {
		this.setBaseDao(useUnitDao);
		this.useUnitDao = useUnitDao;
	}


	

}
