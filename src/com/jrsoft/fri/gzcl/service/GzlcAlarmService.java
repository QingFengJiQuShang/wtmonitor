package com.jrsoft.fri.gzcl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;

public interface GzlcAlarmService   extends BaseService< GzlcAlarm>{
	void export(String filePath,GzlcAlarm entity);

}
