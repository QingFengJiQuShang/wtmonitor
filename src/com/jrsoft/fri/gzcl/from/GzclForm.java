package com.jrsoft.fri.gzcl.from;

import com.jrsoft.fri.common.core.form.BaseForm;
import com.jrsoft.fri.gzcl.entity.GzclFault;
import com.jrsoft.fri.gzcl.entity.GzlcAlarm;

public class GzclForm extends BaseForm{
	
	private GzlcAlarm alarm=new GzlcAlarm();
	private GzclFault fault=new GzclFault();
	
	public GzlcAlarm getAlarm() {
		return alarm;
	}
	public void setAlarm(GzlcAlarm alarm) {
		this.alarm = alarm;
	}
	public GzclFault getFault() {
		return fault;
	}
	public void setFault(GzclFault fault) {
		this.fault = fault;
	}
	
	
	

}
