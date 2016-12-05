package com.jrsoft.fri.xtsz.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import smart.sys.platform.springUtils.SpringBeanUtil;

import com.jrsoft.fri.xtgl.entity.XtglUsers;
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
	
	/**
	 * 系统操作日志
	 * @param log
	 */
	public void addLog(String user,String str,String flag) {
		 XtszLog log=new XtszLog();
		 log.setFoundTime(new Date());
		 log.setUserName(user);
		 log.setContent(str);
		 log.setFlag(flag);
		logService.save(log);
	}
	
}
