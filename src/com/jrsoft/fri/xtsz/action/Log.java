package com.jrsoft.fri.xtsz.action;

import smart.sys.platform.springUtils.SpringBeanUtil;

import com.jrsoft.fri.xtsz.entity.XtszLog;
import com.jrsoft.fri.xtsz.service.XtszLogService;

public class Log {
	private static XtszLogService logService = (XtszLogService)SpringBeanUtil.getBean("logService");

	public static XtszLogService getLogService() {
		return logService;
	}

	public static void setLogService(XtszLogService logService) {
		Log.logService = logService;
	}
	
	/**
	 * 系统操作日志
	 * @param log
	 */
	public void addEntity(XtszLog log) {
		logService.save(log);
	}
	
}
