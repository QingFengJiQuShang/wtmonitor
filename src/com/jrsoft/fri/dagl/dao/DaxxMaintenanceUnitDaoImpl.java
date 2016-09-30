package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUnit;

public class DaxxMaintenanceUnitDaoImpl  extends BaseDaoImpl< DaxxMaintenanceUnit, String> implements  DaxxMaintenanceUnitDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxMaintenanceUnit.class;
	}


}
