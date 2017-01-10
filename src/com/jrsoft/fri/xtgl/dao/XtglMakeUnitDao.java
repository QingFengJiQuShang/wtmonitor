package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglMakeUnit;

public interface XtglMakeUnitDao   extends BaseDao<XtglMakeUnit, String>{
	/**
	 * 
	 * 导出excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglMakeUnit unit);
	
	/**
	 * 导出 excel导入模板
	 * @param filePath
	 */
	void exportModel(String filePath);

}
