package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public interface XtszLogService    extends BaseService< XtszLog>{
	void export(String filePath,XtszLog entity);
}
