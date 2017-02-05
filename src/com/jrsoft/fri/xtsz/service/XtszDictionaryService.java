package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public interface XtszDictionaryService   extends BaseService< XtszDictionary>{
	/**
	 * 查询首页弹窗 提醒时间 
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	public String getDictionary(String flag);
}
