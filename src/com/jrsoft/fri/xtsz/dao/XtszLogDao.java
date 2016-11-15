package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public interface XtszLogDao  extends BaseDao<XtszLog, String>{
	void export(String filePath,XtszLog entity);
}
