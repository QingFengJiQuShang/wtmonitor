package com.jrsoft.fri.bxgl.service;

import java.util.List;

import com.jrsoft.fri.bxgl.dao.BxglSafeDao;
import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.bxgl.from.Safe;
import com.jrsoft.fri.bxgl.from.SafeUnit;
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

	@Override
	public void exportSafe(String filePath, List<Safe> safe) {
		safeDao.exportSafe(filePath, safe);
		
	}

	@Override
	public void exportSafeUnit(String filePath, List<SafeUnit> unit) {
		safeDao.exportSafeUnit(filePath, unit);
		
	}
	
	

}
