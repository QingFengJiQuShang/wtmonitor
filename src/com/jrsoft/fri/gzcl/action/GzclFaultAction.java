package com.jrsoft.fri.gzcl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.gzcl.service.GzclFaultService;

public class GzclFaultAction extends DispatchAction {
	
	private GzclFaultService faultService;

	public GzclFaultService getFaultService() {
		return faultService;
	}

	public void setFaultService(GzclFaultService faultService) {
		this.faultService = faultService;
	}

}
