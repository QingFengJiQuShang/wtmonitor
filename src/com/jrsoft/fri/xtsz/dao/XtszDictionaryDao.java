package com.jrsoft.fri.xtsz.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public interface XtszDictionaryDao    extends BaseDao<XtszDictionary, String>{
	/**
	 * ��ѯ��ҳ���� ����ʱ�� 
	 * @param userId �û�ID
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public String getDictionary(String flag);
}
