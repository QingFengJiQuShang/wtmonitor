package com.jrsoft.fri.bxgl.service;

import com.jrsoft.fri.bxgl.dao.BxglClaimDao;
import com.jrsoft.fri.bxgl.entity.BxglClaim;
import com.jrsoft.fri.common.core.service.BaseServiceImpl;

public class BxglClaimServiceImpl   extends BaseServiceImpl<BxglClaim> implements BxglClaimService{
	private BxglClaimDao claimDao;

	public BxglClaimDao getClaimDao() {
		return claimDao;
	}

	public void setClaimDao(BxglClaimDao claimDao) {
		this.setBaseDao( claimDao);
		this.claimDao = claimDao;
	}

	
	
}
