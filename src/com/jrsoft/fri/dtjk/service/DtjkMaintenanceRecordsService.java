package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;

public interface DtjkMaintenanceRecordsService extends BaseService< DtjkMaintenanceRecords>{
	void export(String filePath,DtjkMaintenanceRecords records);
}
