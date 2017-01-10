package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;

public interface XtglPropertyUnitService   extends BaseService< XtglPropertyUnit>{
	void export(String filePath,XtglPropertyUnit unit);

}
