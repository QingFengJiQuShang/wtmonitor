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
	 * ����flag ��ѯ�����ֵ䣨��ҳ��������ʱ�䡢ҳ��ˢ��ʱ�䣩
	 * @param userId �û�ID
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public static String getDictionary(String flag){
		
		return dictionaryService.getDictionary(flag);
	}
	
}
