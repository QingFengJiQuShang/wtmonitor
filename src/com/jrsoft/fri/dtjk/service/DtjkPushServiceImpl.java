package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkPushDao;
import com.jrsoft.fri.dtjk.entity.DtjkPush;

public class DtjkPushServiceImpl   extends BaseServiceImpl<DtjkPush> implements DtjkPushService{
	
	private DtjkPushDao pushDao;

	public DtjkPushDao getPushDao() {
		return pushDao;
	}

	public void setPushDao(DtjkPushDao pushDao) {
		this.setBaseDao(pushDao);
		this.pushDao = pushDao;
	}
	
	
	
}
