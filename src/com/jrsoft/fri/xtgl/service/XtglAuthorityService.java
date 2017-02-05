package com.jrsoft.fri.xtgl.service;

import com.jrsoft.fri.common.core.service.BaseService;
import com.jrsoft.fri.xtgl.entity.XtglAuthority;

public interface XtglAuthorityService   extends BaseService<XtglAuthority>{

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
