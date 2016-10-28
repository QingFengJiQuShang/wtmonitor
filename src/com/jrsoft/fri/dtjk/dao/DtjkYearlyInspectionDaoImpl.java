package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;

public class DtjkYearlyInspectionDaoImpl  extends BaseDaoImpl< DtjkYearlyInspection, String> implements  DtjkYearlyInspectionDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkYearlyInspection.class;
	}

}
