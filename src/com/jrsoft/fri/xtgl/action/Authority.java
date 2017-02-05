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
	 * 查看用户是否拥有key资源元素
	 * @param userId 用户ID
	 * @param key 资源元素key
	 * @return
	 */
	public static boolean haveRigth(Long userId,String key){
		
		return authorityService.getByUser(userId, key);
	}
	/**
	 * 查看用户是否拥有key资源元素  短信权限 
	 * @param key 资源元素key
	 * @return
	 */
	public static boolean haveMessage(String key){
		
		return authorityService.getByMessage( key);
	}
	/**
	 * 查看 是否拥有key资源元素   报警控制权限
	 * @param key 资源元素key
	 * @return
	 */
	public static boolean getByPush(String key){
		return authorityService.getByPush( key);
	}

}
