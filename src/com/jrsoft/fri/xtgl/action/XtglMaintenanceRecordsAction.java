package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglMaintenanceRecordsService;

public class XtglMaintenanceRecordsAction extends DispatchAction {
	
	private XtglMaintenanceRecordsService recordsService;

	public XtglMaintenanceRecordsService getRecordsService() {
		return recordsService;
	}

	public void setRecordsService(XtglMaintenanceRecordsService recordsService) {
		this.recordsService = recordsService;
	}
	

}
