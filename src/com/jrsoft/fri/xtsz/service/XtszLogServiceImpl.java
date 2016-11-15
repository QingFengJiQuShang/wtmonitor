package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.XtszLogDao;
import com.jrsoft.fri.xtsz.entity.XtszLog;

public class XtszLogServiceImpl   extends BaseServiceImpl<XtszLog> implements XtszLogService{
	private XtszLogDao logDao;

	public XtszLogDao getLogDao() {
		return logDao;
	}

	public void setLogDao(XtszLogDao logDao) {
		this.setBaseDao(logDao);
		this.logDao = logDao;
	}

	@Override
	public void export(String filePath, XtszLog entity) {
		logDao.export(filePath, entity);
		
	}
	
	
}
