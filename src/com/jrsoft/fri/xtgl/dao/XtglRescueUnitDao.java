package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public interface XtglRescueUnitDao   extends BaseDao<XtglRescueUnit, String>{
	void export(String filePath,XtglRescueUnit unit);
}
