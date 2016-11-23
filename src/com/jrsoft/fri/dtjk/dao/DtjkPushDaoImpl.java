package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkPush;

public class DtjkPushDaoImpl  extends BaseDaoImpl< DtjkPush, String> implements  DtjkPushDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkPush.class;
	}

}
