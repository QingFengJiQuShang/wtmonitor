package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dagl.entity.DaxxMaintenanceUnit;

public interface DaxxMaintenanceUnitDao  extends BaseDao<DaxxMaintenanceUnit, String>{
	void export(String filePath,DaxxMaintenanceUnit gateway);
}
