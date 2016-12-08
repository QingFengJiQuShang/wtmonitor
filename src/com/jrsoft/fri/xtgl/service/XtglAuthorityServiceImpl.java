package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseServiceImpl;
import com.jrsoft.fri.xtgl.dao.XtglAuthorityDao;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;

public class XtglAuthorityServiceImpl  extends BaseServiceImpl<XtglAuthority> implements XtglAuthorityService{
	
	private XtglAuthorityDao authorityDao;

	public XtglAuthorityDao getAuthorityDao() {
		return authorityDao;
	}

	public void setAuthorityDao(XtglAuthorityDao authorityDao) {
		this.setBaseDao(authorityDao);
		this.authorityDao = authorityDao;
	}

	/**
	 * 查看用户是否拥有key资源元素
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	@Override
	public boolean getByUser(Long userId, String key) {
		
		return authorityDao.getByUser(userId, key);
	}

	@Override
	public boolean getByMessage(String key) {
		
		return authorityDao.getByMessage(key);
	}
	

}
