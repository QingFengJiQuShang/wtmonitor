package com.jrsoft.fri.dagl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dagl.service.DaxxUseUnitService;

public class DaxxUseUnitAction  extends DispatchAction  {
	private DaxxUseUnitService useUnitService;

	public DaxxUseUnitService getUseUnitService() {
		return useUnitService;
	}

	public void setUseUnitService(DaxxUseUnitService useUnitService) {
		this.useUnitService = useUnitService;
	}
	
	

}
