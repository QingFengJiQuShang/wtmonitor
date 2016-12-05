package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.xtsz.entity.XtszHelp;

public class XtszHelpDaoImpl   extends BaseDaoImpl< XtszHelp, String> implements  XtszHelpDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return XtszHelp.class;
	}

}
