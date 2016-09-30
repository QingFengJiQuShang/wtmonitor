package com.jrsoft.fri.dagl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dagl.dao.DaxxGatewayDao;
import com.jrsoft.fri.dagl.entity.DaxxGateway;

public class DaxxGatewayServiceImpl    extends BaseServiceImpl<DaxxGateway, String> implements DaxxGatewayService{
	private DaxxGatewayDao gatewayDao;

	public DaxxGatewayDao getGatewayDao() {
		return gatewayDao;
	}

	public void setGatewayDao(DaxxGatewayDao gatewayDao) {
		this.setBaseDao(gatewayDao);
		this.gatewayDao = gatewayDao;
	}


	
}
