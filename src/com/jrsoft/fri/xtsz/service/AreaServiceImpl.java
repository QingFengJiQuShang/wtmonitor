package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.AreaDao;
import com.jrsoft.fri.xtsz.entity.Area;

public class AreaServiceImpl   extends BaseServiceImpl<Area> implements AreaService{
	private AreaDao areaDao;

	public AreaDao getAreaDao() {
		return areaDao;
	}

	public void setAreaDao(AreaDao areaDao) {
		this.setBaseDao(areaDao);
		this.areaDao = areaDao;
	}

	
}
