package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;

public interface XtglMakeUnitService   extends BaseService< XtglMakeUnit>{
	void export(String filePath,XtglMakeUnit unit);

}
