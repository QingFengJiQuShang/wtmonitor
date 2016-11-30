package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;

public interface XtglAuthorityDao  extends BaseDao<XtglAuthority, String>{
	
	/**
	 * 查看用户是否拥有key资源元素
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	public boolean getByUser(String userId,String key);

}
