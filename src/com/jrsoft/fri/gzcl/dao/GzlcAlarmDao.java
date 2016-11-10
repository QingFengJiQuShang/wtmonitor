package com.jrsoft.fri.gzcl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;

public interface GzlcAlarmDao   extends BaseDao<GzlcAlarm, String>{
	void export(String filePath,GzlcAlarm entity);
}
