package com.jrsoft.fri.xtgl.action;

import smart.sys.platform.springUtils.SpringBeanUtil;
import com.jrsoft.fri.xtgl.service.XtglAuthorityService;

public class Authority {
	private static XtglAuthorityService authorityService = (XtglAuthorityService)SpringBeanUtil.getBean("authorityService");

	public static XtglAuthorityService getAuthorityService() {
		return authorityService;
	}

	public static void setAuthorityService(XtglAuthorityService authorityService) {
		Authority.authorityService = authorityService;
	}
	
	/**
	 * �鿴�û��Ƿ�ӵ��key��ԴԪ��
	 * @param userId �û�ID
	 * @param key ��ԴԪ��key
	 * @return
	 */
	public boolean haveRigth(String userId,String key){
		
		return authorityService.getByUser(userId, key);
	}


}
