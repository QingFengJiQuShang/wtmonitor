package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;

public interface XtglMaintenanceUnitDao  extends BaseDao<XtglMaintenanceUnit, String>{
	void export(String filePath,XtglMaintenanceUnit gateway);
}
