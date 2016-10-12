package com.jrsoft.fri.dagl.action;

import org.apache.struts.actions.DispatchAction;

import com.jrsoft.fri.dagl.service.DaxxGatewayService;

public class DaxxGatewayAction  extends DispatchAction {
	private DaxxGatewayService gatewayService;

	public DaxxGatewayService getGatewayService() {
		return gatewayService;
	}

	public void setGatewayService(DaxxGatewayService gatewayService) {
		this.gatewayService = gatewayService;
	}
	
	

}
