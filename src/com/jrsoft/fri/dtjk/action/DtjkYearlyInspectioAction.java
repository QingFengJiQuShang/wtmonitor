package com.jrsoft.fri.dtjk.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dtjk.service.DtjkYearlyInspectionService;

public class DtjkYearlyInspectioAction extends DispatchAction {
	
	private DtjkYearlyInspectionService inspectionService;

	public DtjkYearlyInspectionService getInspectionService() {
		return inspectionService;
	}

	public void setInspectionService(DtjkYearlyInspectionService inspectionService) {
		this.inspectionService = inspectionService;
	}
	
	

}
