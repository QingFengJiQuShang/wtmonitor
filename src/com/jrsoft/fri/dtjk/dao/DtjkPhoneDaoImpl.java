package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;

public class DtjkPhoneDaoImpl  extends BaseDaoImpl< DtjkPhone, String> implements  DtjkPhoneDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return DtjkPhone.class;
	}

}
