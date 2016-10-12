package com.jrsoft.fri.dagl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dagl.service.DaxxMaintenanceUsersService;

public class DaxxMaintenanceUsersAction  extends DispatchAction {
	private DaxxMaintenanceUsersService maintenanceUsersService;

	public DaxxMaintenanceUsersService getMaintenanceUsersService() {
		return maintenanceUsersService;
	}

	public void setMaintenanceUsersService(
			DaxxMaintenanceUsersService maintenanceUsersService) {
		this.maintenanceUsersService = maintenanceUsersService;
	}
	
	
	

}
