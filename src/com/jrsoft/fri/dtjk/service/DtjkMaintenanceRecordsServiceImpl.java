package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkMaintenanceRecordsDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;

public class DtjkMaintenanceRecordsServiceImpl   extends BaseServiceImpl<DtjkMaintenanceRecords> implements DtjkMaintenanceRecordsService{
	private DtjkMaintenanceRecordsDao recordsDao;

	public DtjkMaintenanceRecordsDao getRecordsDao() {
		return recordsDao;
	}

	public void setRecordsDao(DtjkMaintenanceRecordsDao recordsDao) {
		this.setBaseDao(recordsDao);
		this.recordsDao = recordsDao;
	}

	@Override
	public void export(String filePath, DtjkMaintenanceRecords records) {
		recordsDao.export(filePath, records);
	}


	
}
