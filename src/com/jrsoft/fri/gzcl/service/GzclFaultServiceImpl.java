package com.jrsoft.fri.gzcl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.gzcl.dao.GzclFaultDao;
import com.jrsoft.fri.gzcl.entity.GzclFault;

public class GzclFaultServiceImpl   extends BaseServiceImpl<GzclFault> implements GzclFaultService{
	private GzclFaultDao faultDao;

	public GzclFaultDao getFaultDao() {
		return faultDao;
	}

	public void setFaultDao(GzclFaultDao faultDao) {
		this.faultDao = faultDao;
	}

}
