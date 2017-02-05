package com.jrsoft.fri.xtsz.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtsz.dao.XtszDictionaryDao;
import com.jrsoft.fri.xtsz.entity.XtszDictionary;

public class XtszDictionaryServiceImpl  extends BaseServiceImpl<XtszDictionary> implements XtszDictionaryService{
	private XtszDictionaryDao dictionaryDao;

	public XtszDictionaryDao getDictionaryDao() {
		return dictionaryDao;
	}

	public void setDictionaryDao(XtszDictionaryDao dictionaryDao) {
		this.setBaseDao(dictionaryDao);
		this.dictionaryDao = dictionaryDao;
	}

	@Override
	public String getDictionary(String flag) {

		return dictionaryDao.getDictionary(flag);
	}

}
