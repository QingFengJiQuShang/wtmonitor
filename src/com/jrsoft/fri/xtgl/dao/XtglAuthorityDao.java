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
	public boolean getByUser(Long userId,String key);
	
	/**
	 * 查看 是否拥有key资源元素   短信权限
	 * @param key 资源元素key
	 * @return
	 */
	public boolean getByMessage(String key);
	
	/**
	 * 查看 是否拥有key资源元素   报警控制权限
	 * @param key 资源元素key
	 * @return
	 */
	public boolean getByPush(String key);

}
