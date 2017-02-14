package com.jrsoft.fri.bxgl.dao;

import com.jrsoft.fri.bxgl.entity.BxglClaim;
import com.jrsoft.fri.common.core.dao.BaseDaoImpl;

public class BxglClaimDaoImpl  extends BaseDaoImpl< BxglClaim, String> implements  BxglClaimDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return BxglClaim.class;
	}
}
