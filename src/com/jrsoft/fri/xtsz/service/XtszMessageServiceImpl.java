package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.XtszMessageDao;
import com.jrsoft.fri.xtsz.entity.XtszMessage;

public class XtszMessageServiceImpl   extends BaseServiceImpl<XtszMessage> implements XtszMessageService{
	private XtszMessageDao messageDao;


	public XtszMessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(XtszMessageDao messageDao) {
		this.setBaseDao(messageDao);
		this.messageDao = messageDao;
	}
	
}
