package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;

public interface XtglMakeUnitDao   extends BaseDao<XtglMakeUnit, String>{
	/**
	 * 
	 * ����excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglMakeUnit unit);
	
	/**
	 * ���� excel����ģ��
	 * @param filePath
	 */
	void exportModel(String filePath);

}
