package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkServiceDao;
import com.jrsoft.fri.dtjk.entity.DtjkService;


public class DtjkServiceServiceImpl  extends BaseServiceImpl<DtjkService> implements DtjkServiceService{
		private DtjkServiceDao serviceDao;

		public DtjkServiceDao getServiceDao() {
			return serviceDao;
		}

		public void setServiceDao(DtjkServiceDao serviceDao) {
			this.setBaseDao(serviceDao);
			this.serviceDao = serviceDao;
		}
		
		
}
