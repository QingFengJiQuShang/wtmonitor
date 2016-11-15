package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkPhoneDao;
import com.jrsoft.fri.dtjk.entity.DtjkPhone;

public class DtjkPhoneServiceImpl    extends BaseServiceImpl<DtjkPhone> implements DtjkPhoneService{
	private DtjkPhoneDao phoneDao;

	public DtjkPhoneDao getPhoneDao() {
		return phoneDao;
	}

	public void setPhoneDao(DtjkPhoneDao phoneDao) {
		this.setBaseDao(phoneDao);
		this.phoneDao = phoneDao;
	}
	
}
