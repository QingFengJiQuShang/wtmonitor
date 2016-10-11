package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxRegionDao;
import com.jrsoft.fri.dagl.entity.DaxxRegion;

public class DaxxRegionServiceImpl    extends BaseServiceImpl<DaxxRegion> implements DaxxRegionService{
	private DaxxRegionDao regionDao;

	public DaxxRegionDao getRegionDao() {
		return regionDao;
	}

	public void setRegionDao(DaxxRegionDao regionDao) {
		this.setBaseDao(regionDao);
		this.regionDao = regionDao;
	}


	

}
