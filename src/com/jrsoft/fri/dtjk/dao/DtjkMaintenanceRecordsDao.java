package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkMaintenanceRecords;

public interface DtjkMaintenanceRecordsDao  extends BaseDao<DtjkMaintenanceRecords, String>{
	void export(String filePath,DtjkMaintenanceRecords records);

}
