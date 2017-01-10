package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglRegionUnitService;

public class XtglRegionUnitAction extends DispatchAction  {
	private XtglRegionUnitService regionUnitService;

	public XtglRegionUnitService getRegionUnitService() {
		return regionUnitService;
	}

	public void setRegionUnitService(XtglRegionUnitService regionUnitService) {
		this.regionUnitService = regionUnitService;
	}
	

}
