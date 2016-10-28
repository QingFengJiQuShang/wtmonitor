package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.dtjk.entity.DtjkRecord;

public interface DtjkRecordService    extends BaseService< DtjkRecord>{
	void export(String filePath,DtjkRecord elevator);
}
