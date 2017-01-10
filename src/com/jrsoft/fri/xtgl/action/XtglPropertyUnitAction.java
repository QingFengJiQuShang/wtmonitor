package com.jrsoft.fri.xtgl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.xtgl.service.XtglPropertyUnitService;

public class XtglPropertyUnitAction extends DispatchAction  {
	private XtglPropertyUnitService propertyUnitService;

	public XtglPropertyUnitService getPropertyUnitService() {
		return propertyUnitService;
	}

	public void setPropertyUnitService(XtglPropertyUnitService propertyUnitService) {
		this.propertyUnitService = propertyUnitService;
	}
	

}
