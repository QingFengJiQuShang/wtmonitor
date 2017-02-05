package com.jrsoft.fri.xtsz.action;

import com.jrsoft.fri.xtsz.service.XtszDictionaryService;

import smart.sys.platform.springUtils.SpringBeanUtil;


public class Dictionary {
	private static XtszDictionaryService dictionaryService = (XtszDictionaryService)SpringBeanUtil.getBean("dictionaryService");

	public static XtszDictionaryService getDictionaryService() {
		return dictionaryService;
	}
	public static void setDictionaryService(XtszDictionaryService dictionaryService) {
		Dictionary.dictionaryService = dictionaryService;
	}



	/**
	 * 根据flag 查询数据字典（首页弹窗提醒时间、页面刷新时间）
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	public static String getDictionary(String flag){
		
		return dictionaryService.getDictionary(flag);
	}
	
}
