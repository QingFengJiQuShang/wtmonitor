package com.jrsoft.fri.gzcl.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.dao.DtjkGatewayDao;
import com.jrsoft.fri.dtjk.entity.DtjkGateway;
import com.jrsoft.fri.gzcl.entity.GzclFault;

public class GzclFaultDaoImpl  extends BaseDaoImpl< GzclFault, String> implements  GzclFaultDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  GzclFault.class;
	}

}
