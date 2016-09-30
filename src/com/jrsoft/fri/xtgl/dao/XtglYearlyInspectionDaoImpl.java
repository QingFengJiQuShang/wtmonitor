package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglYearlyInspection;

public class XtglYearlyInspectionDaoImpl  extends BaseDaoImpl< XtglYearlyInspection, String> implements  XtglYearlyInspectionDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtglYearlyInspection.class;
	}

}
