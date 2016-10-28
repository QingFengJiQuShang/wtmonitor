package com.jrsoft.fri.gzcl.action;

import org.apache.struts.actions.DispatchAction;
import com.jrsoft.fri.gzcl.service.GzlcAlarmService;

public class GzlcAlarmAction  extends DispatchAction {
	
	private GzlcAlarmService alarmService ;

	public GzlcAlarmService getAlarmService() {
		return alarmService;
	}

	public void setAlarmService(GzlcAlarmService alarmService) {
		this.alarmService = alarmService;
	}

}
