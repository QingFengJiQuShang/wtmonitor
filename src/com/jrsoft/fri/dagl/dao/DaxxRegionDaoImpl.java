package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxRegion;

public class DaxxRegionDaoImpl  extends BaseDaoImpl< DaxxRegion, String> implements  DaxxRegionDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxRegion.class;
	}


}
