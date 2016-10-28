package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;

public class DtjkMaintenanceRecordsDaoImpl extends BaseDaoImpl< DtjkMaintenanceRecords, String> implements  DtjkMaintenanceRecordsDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkMaintenanceRecords.class;
	}

}
