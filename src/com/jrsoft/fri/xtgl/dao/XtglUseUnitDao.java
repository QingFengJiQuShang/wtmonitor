package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public interface XtglUseUnitDao  extends BaseDao<XtglUseUnit, String>{
	/**
	 * 
	 * ����excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglUseUnit unit);
	
	/**
	 * ���� excel����ģ��
	 * @param filePath
	 */
	void exportModel(String filePath);

}
