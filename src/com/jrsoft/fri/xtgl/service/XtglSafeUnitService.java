package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglSafeUnit;

public interface XtglSafeUnitService   extends BaseService< XtglSafeUnit>{
	void export(String filePath,XtglSafeUnit unit);

}
