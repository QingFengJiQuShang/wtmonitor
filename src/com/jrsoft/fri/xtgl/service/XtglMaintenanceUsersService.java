package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUnit;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;

public interface XtglMaintenanceUsersService   extends BaseService< XtglMaintenanceUsers>{
	void export(String filePath,XtglMaintenanceUsers users);
}
