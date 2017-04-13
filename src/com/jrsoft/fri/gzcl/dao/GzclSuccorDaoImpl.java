package com.jrsoft.fri.gzcl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.gzcl.entity.GzclSuccor;

public class GzclSuccorDaoImpl  extends BaseDaoImpl< GzclSuccor, String> implements  GzclSuccorDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzclSuccor.class;
	}

}
