package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;

public interface DtjkRecordDao  extends BaseDao<DtjkRecord, String>{
	void export(String filePath,DtjkRecord elevator);
}
