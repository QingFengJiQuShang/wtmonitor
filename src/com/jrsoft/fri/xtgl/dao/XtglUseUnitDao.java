package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public interface XtglUseUnitDao  extends BaseDao<XtglUseUnit, String>{
	void export(String filePath,XtglUseUnit unit);
}
