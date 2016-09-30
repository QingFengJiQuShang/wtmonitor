package com.jrsoft.fri.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * ��ȡspring�������Է��������ж��������bean
 */
public class SpringContextUtil implements ApplicationContextAware {
 
	// SpringӦ�������Ļ���
	private static ApplicationContext applicationContext;
 
	/**
	 * ʵ��ApplicationContextAware�ӿڵĻص����������������Ļ���
	 */
	public void setApplicationContext(ApplicationContext applicationContext)throws BeansException  {
		SpringContextUtil.applicationContext = applicationContext;
	}
 
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
 
	/**
	 * ��ȡ����
	 * ������д��bean����������Ҫ����
	 * @param name
	 * @return Object һ������������ע���bean��ʵ��
	 * @throws BeansException
	 */
	public static Object getBean(String beanId) throws BeansException {
		return applicationContext.getBean(beanId);
	}
}
