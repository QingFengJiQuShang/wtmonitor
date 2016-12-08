package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.XtszMessage;

public class XtszMessageDaoImpl   extends BaseDaoImpl< XtszMessage, String> implements  XtszMessageDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtszMessage.class;
	}

}
