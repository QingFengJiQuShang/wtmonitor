package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.XtszHelpDao;
import com.jrsoft.fri.xtsz.entity.XtszHelp;

public class XtszHelpServiceImpl   extends BaseServiceImpl<XtszHelp> implements XtszHelpService{
	private XtszHelpDao helpDao;

	public XtszHelpDao getHelpDao() {
		return helpDao;
	}

	public void setHelpDao(XtszHelpDao helpDao) {
		this.setBaseDao(helpDao);
		this.helpDao = helpDao;
	}
}
