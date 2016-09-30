package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceRecords;

public class XtglMaintenanceRecordsDaoImpl extends BaseDaoImpl< XtglMaintenanceRecords, String> implements  XtglMaintenanceRecordsDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtglMaintenanceRecords.class;
	}

}
