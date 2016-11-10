package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;

public interface DtjkYearlyInspectionService extends BaseService< DtjkYearlyInspection>{
	void export(String filePath,DtjkYearlyInspection inspection );
}
