package com.jrsoft.fri.bxgl.dao;

import com.jrsoft.fri.bxgl.entity.BxglSafe;
import com.jrsoft.fri.common.core.dao.BaseDaoImpl;

public class BxglSafeDaoImpl   extends BaseDaoImpl< BxglSafe, String> implements  BxglSafeDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return BxglSafe.class;
	}
}
