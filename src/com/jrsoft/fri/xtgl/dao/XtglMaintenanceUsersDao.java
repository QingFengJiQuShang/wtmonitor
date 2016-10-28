package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglMaintenanceUsers;

public interface XtglMaintenanceUsersDao  extends BaseDao<XtglMaintenanceUsers, String>{
		void export(String filePath,XtglMaintenanceUsers users);
}
