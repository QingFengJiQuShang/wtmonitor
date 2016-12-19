package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkService;

public class DtjkServiceDaoImpl  extends BaseDaoImpl< DtjkService, String> implements  DtjkServiceDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkService.class;
	}

}
