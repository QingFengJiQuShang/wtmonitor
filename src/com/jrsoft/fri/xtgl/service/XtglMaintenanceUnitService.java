package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;

public interface XtglMaintenanceUnitService   extends BaseService< XtglMaintenanceUnit>{
	void export(String filePath,XtglMaintenanceUnit gateway);
}
