package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.City;

public class CityDaoImpl   extends BaseDaoImpl< City, String> implements  CityDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return City.class;
	}

}
