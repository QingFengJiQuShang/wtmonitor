package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;

public class XtglRescueUnitDaoImpl  extends BaseDaoImpl< XtglRescueUnit, String> implements  XtglRescueUnitDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  XtglRescueUnit.class;
	}
}
