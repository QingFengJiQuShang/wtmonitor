package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglSafeUnitService;

public class XtglSafeUnitAction extends DispatchAction  {
	private XtglSafeUnitService safeUnitService;

	public XtglSafeUnitService getSafeUnitService() {
		return safeUnitService;
	}

	public void setSafeUnitService(XtglSafeUnitService safeUnitService) {
		this.safeUnitService = safeUnitService;
	}
	
	

}
