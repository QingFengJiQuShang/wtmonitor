package com.jrsoft.fri.gzcl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.gzcl.dao.GzclRescueDao;
import com.jrsoft.fri.gzcl.entity.GzclRescue;

public class GzclRescueServiceImpl  extends BaseServiceImpl<GzclRescue> implements GzclRescueService{
	private GzclRescueDao gzclRescueDao;

	public GzclRescueDao getGzclRescueDao() {
		return gzclRescueDao;
	}

	public void setGzclRescueDao(GzclRescueDao gzclRescueDao) {
		this.setBaseDao(gzclRescueDao);
		this.gzclRescueDao = gzclRescueDao;
	}



}
