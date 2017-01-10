package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.Province;

public class ProvinceDaoImpl   extends BaseDaoImpl< Province, String> implements  ProvinceDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return Province.class;
	}

}
