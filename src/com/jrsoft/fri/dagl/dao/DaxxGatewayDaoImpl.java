package com.jrsoft.fri.dagl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dagl.entity.DaxxGateway;

public class DaxxGatewayDaoImpl  extends BaseDaoImpl< DaxxGateway, String> implements  DaxxGatewayDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DaxxGateway.class;
	}


}
