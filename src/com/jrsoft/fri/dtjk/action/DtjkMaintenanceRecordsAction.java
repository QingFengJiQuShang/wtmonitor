package com.jrsoft.fri.dtjk.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dtjk.service.DtjkMaintenanceRecordsService;

public class DtjkMaintenanceRecordsAction extends DispatchAction {
	
	private DtjkMaintenanceRecordsService recordsService;

	public DtjkMaintenanceRecordsService getRecordsService() {
		return recordsService;
	}

	public void setRecordsService(DtjkMaintenanceRecordsService recordsService) {
		this.recordsService = recordsService;
	}
	

}
