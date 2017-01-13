package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public class XtszDictionaryDaoImpl  extends BaseDaoImpl< XtszDictionary, String> implements  XtszDictionaryDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtszDictionary.class;
	}

}
