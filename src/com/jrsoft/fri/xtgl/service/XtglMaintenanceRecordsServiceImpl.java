package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglMaintenanceRecordsDao;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceRecords;

public class XtglMaintenanceRecordsServiceImpl   extends BaseServiceImpl<XtglMaintenanceRecords, String> implements XtglMaintenanceRecordsService{
	private XtglMaintenanceRecordsDao recordsDao;

	public XtglMaintenanceRecordsDao getRecordsDao() {
		return recordsDao;
	}

	public void setRecordsDao(XtglMaintenanceRecordsDao recordsDao) {
		this.setBaseDao(recordsDao);
		this.recordsDao = recordsDao;
	}


	
}
