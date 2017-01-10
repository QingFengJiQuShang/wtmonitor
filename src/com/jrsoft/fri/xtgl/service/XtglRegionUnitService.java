package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglRegionUnit;

public interface XtglRegionUnitService   extends BaseService< XtglRegionUnit>{
	void export(String filePath,XtglRegionUnit unit);

}
