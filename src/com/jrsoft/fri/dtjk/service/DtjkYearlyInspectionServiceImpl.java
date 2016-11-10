package com.jrsoft.fri.dtjk.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.dtjk.dao.DtjkYearlyInspectionDao;
import com.jrsoft.fri.dtjk.entity.DtjkElevator;
import com.jrsoft.fri.dtjk.entity.DtjkYearlyInspection;

public class DtjkYearlyInspectionServiceImpl   extends BaseServiceImpl<DtjkYearlyInspection> implements DtjkYearlyInspectionService{
	private DtjkYearlyInspectionDao inspectionDao;

	
	public DtjkYearlyInspectionDao getInspectionDao() {
		return inspectionDao;
	}

	public void setInspectionDao(DtjkYearlyInspectionDao inspectionDao) {
		this.setBaseDao(inspectionDao);
		this.inspectionDao = inspectionDao;
	}

	@Override
	public void export(String filePath, DtjkYearlyInspection inspection) {
		inspectionDao.export(filePath, inspection);
		
	}


	
	
}
