package com.jrsoft.fri.dtjk.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;

public interface DtjkYearlyInspectionDao extends BaseDao<DtjkYearlyInspection, String>{
	void export(String filePath,DtjkYearlyInspection inspection );
}
