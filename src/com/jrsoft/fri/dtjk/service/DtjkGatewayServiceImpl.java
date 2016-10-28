package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkGatewayDao;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;

public class DtjkGatewayServiceImpl    extends BaseServiceImpl<DtjkGateway> implements DtjkGatewayService{
	private DtjkGatewayDao gatewayDao;

	public DtjkGatewayDao getGatewayDao() {
		return gatewayDao;
	}

	public void setGatewayDao(DtjkGatewayDao gatewayDao) {
		this.setBaseDao(gatewayDao);
		this.gatewayDao = gatewayDao;
	}



	
}
