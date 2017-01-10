package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.Area;

public class AreaDaoImpl   extends BaseDaoImpl< Area, String> implements  AreaDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return Area.class;
	}

}
