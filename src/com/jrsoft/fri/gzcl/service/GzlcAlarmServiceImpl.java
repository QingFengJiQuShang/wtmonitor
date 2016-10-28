package com.jrsoft.fri.gzcl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.gzcl.dao.GzlcAlarmDao;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;

public class GzlcAlarmServiceImpl   extends BaseServiceImpl<GzlcAlarm> implements GzlcAlarmService{
	private GzlcAlarmDao alarmDao;

	public GzlcAlarmDao getAlarmDao() {
		return alarmDao;
	}

	public void setAlarmDao(GzlcAlarmDao alarmDao) {
		this.alarmDao = alarmDao;
	}

	

}
