package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglPropertyUnit;

public interface XtglPropertyUnitDao   extends BaseDao<XtglPropertyUnit, String>{
	/**
	 * 
	 * ����excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglPropertyUnit unit);
	
	/**
	 * ���� excel����ģ��
	 * @param filePath
	 */
	void exportModel(String filePath);

}
