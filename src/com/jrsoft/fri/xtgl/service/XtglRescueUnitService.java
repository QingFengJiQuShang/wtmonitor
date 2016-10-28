package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglRescueUnit;

public interface XtglRescueUnitService   extends BaseService< XtglRescueUnit>{
	void export(String filePath,XtglRescueUnit gateway);

}
