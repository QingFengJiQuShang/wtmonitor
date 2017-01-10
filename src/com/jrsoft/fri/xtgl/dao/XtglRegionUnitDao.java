package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglRegionUnit;

public interface XtglRegionUnitDao   extends BaseDao<XtglRegionUnit, String>{
	/**
	 * 
	 * ����excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglRegionUnit unit);
	
	/**
	 * ���� excel����ģ��
	 * @param filePath
	 */
	void exportModel(String filePath);

}
