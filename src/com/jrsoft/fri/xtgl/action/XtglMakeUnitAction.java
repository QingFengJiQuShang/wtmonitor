package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglMakeUnitService;

public class XtglMakeUnitAction extends DispatchAction  {
	private XtglMakeUnitService makeUnitService;

	public XtglMakeUnitService getMakeUnitService() {
		return makeUnitService;
	}

	public void setMakeUnitService(XtglMakeUnitService makeUnitService) {
		this.makeUnitService = makeUnitService;
	}
	

}
