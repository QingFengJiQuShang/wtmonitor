package com.jrsoft.fri.bxgl.service;

import com.jrsoft.fri.bxgl.dao.BxglSafeDao;
import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.common.core.service.BaseServiceImpl;

public class BxglSafeServiceImpl   extends BaseServiceImpl<BxglSafe> implements BxglSafeService{
	private BxglSafeDao safeDao;

	public BxglSafeDao getSafeDao() {
		return safeDao;
	}

	public void setSafeDao(BxglSafeDao safeDao) {
		this.setBaseDao(safeDao);
		this.safeDao = safeDao;
	}
	
	

}
