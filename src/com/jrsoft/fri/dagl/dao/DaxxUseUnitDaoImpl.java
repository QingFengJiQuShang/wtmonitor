package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxUseUnit;

public class DaxxUseUnitDaoImpl  extends BaseDaoImpl< DaxxUseUnit, String> implements  DaxxUseUnitDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxUseUnit.class;
	}


}
