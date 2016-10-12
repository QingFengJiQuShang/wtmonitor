package com.jrsoft.fri.dagl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dagl.service.DaxxMaintenanceUnitService;

public class DaxxMaintenanceUnitAction  extends DispatchAction {
	private DaxxMaintenanceUnitService maintenanceUnitService;

	public DaxxMaintenanceUnitService getMaintenanceUnitService() {
		return maintenanceUnitService;
	}

	public void setMaintenanceUnitService(
			DaxxMaintenanceUnitService maintenanceUnitService) {
		this.maintenanceUnitService = maintenanceUnitService;
	}
	

}
