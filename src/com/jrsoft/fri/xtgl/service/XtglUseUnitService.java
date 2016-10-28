package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public interface XtglUseUnitService   extends BaseService< XtglUseUnit>{
	void export(String filePath,XtglUseUnit unit);

}
