package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglYearlyInspectionService;

public class XtglYearlyInspectioAction extends DispatchAction {
	
	private XtglYearlyInspectionService inspectionService;

	public XtglYearlyInspectionService getInspectionService() {
		return inspectionService;
	}

	public void setInspectionService(XtglYearlyInspectionService inspectionService) {
		this.inspectionService = inspectionService;
	}
	
	

}
