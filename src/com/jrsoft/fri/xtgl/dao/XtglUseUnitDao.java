package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglUseUnit;

public interface XtglUseUnitDao  extends BaseDao<XtglUseUnit, String>{
	/**
	 * 
	 * 导出excel
	 * @param filePath
	 * @param unit
	 */
	void export(String filePath,XtglUseUnit unit);
	
	/**
	 * 导出 excel导入模板
	 * @param filePath
	 */
	void exportModel(String filePath);

}
