package com.jrsoft.fri.dtjk.dao;


import com.jrsoft.fri.common.core.dao.BaseDaoImpl;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;

public class DtjkRecordDaoImpl extends BaseDaoImpl< DtjkRecord, String> implements  DtjkRecordDao{

	@Override
	public Class<? extends Object> getEntityClass() {
		// TODO Auto-generated method stub
		return  DtjkRecord.class;
	}

	@Override
	public void export(String filePath, DtjkRecord elevator) {}

}
