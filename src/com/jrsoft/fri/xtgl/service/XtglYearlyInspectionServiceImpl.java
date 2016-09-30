package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglYearlyInspectionDao;
import com.jrsoft.fri.xtgl.entity.XtglYearlyInspection;

public class XtglYearlyInspectionServiceImpl   extends BaseServiceImpl<XtglYearlyInspection, String> implements XtglYearlyInspectionService{
	private XtglYearlyInspectionDao inspectionDao;

	
	public XtglYearlyInspectionDao getInspectionDao() {
		return inspectionDao;
	}

	public void setInspectionDao(XtglYearlyInspectionDao inspectionDao) {
		this.setBaseDao(inspectionDao);
		this.inspectionDao = inspectionDao;
	}


	
	
}
