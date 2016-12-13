package com.jrsoft.fri.gzcl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.gzcl.entity.GzclRescue;

public class GzclRescueDaoImpl  extends BaseDaoImpl< GzclRescue, String> implements  GzclRescueDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzclRescue.class;
	}
}
