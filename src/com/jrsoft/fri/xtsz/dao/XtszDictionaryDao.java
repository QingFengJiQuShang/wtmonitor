package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public interface XtszDictionaryDao    extends BaseDao<XtszDictionary, String>{
	/**
	 * 查询首页弹窗 提醒时间 
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	public String getDictionary(String flag);
}
