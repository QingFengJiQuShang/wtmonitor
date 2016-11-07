package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkRecordDao;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;

public class DtjkRecordServiceImpl    extends BaseServiceImpl<DtjkRecord> implements DtjkRecordService{
	private DtjkRecordDao recordDao;

	public DtjkRecordDao getRecordDao() {
		return recordDao;
	}
	public void setRecordDao(DtjkRecordDao recordDao) {
		this.setBaseDao(recordDao);
		this.recordDao = recordDao;
	}



	@Override
	public void export(String filePath, DtjkRecord elevator) {
		recordDao.export(filePath, elevator);
		
	}

}
