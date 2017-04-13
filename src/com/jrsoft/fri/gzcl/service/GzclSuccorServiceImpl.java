package com.jrsoft.fri.gzcl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.gzcl.dao.GzclSuccorDao;
import com.jrsoft.fri.gzcl.entity.GzclSuccor;

public class GzclSuccorServiceImpl   extends BaseServiceImpl<GzclSuccor> implements GzclSuccorService{
	private GzclSuccorDao succorDao;

	public GzclSuccorDao getSuccorDao() {
		return succorDao;
	}

	public void setSuccorDao(GzclSuccorDao succorDao) {
		this.setBaseDao(succorDao);
		this.succorDao = succorDao;
	}


}
