package com.jrsoft.fri.xtgl.dao;

import com.jrsoft.fri.common.core.dao.BaseDao;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;

public interface XtglAuthorityDao  extends BaseDao<XtglAuthority, String>{
	
	/**
	 * �鿴�û��Ƿ�ӵ��key��ԴԪ��
	 * @param userId �û�ID
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public boolean getByUser(Long userId,String key);
	
	/**
	 * �鿴 �Ƿ�ӵ��key��ԴԪ��   ����Ȩ��
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public boolean getByMessage(String key);
	
	/**
	 * �鿴 �Ƿ�ӵ��key��ԴԪ��   ��������Ȩ��
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public boolean getByPush(String key);

}
