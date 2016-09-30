package com.jrsoft.fri.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectUtil {
	/**
	 * 
	 */
	public static String getFieldClassName(Class<? extends Object> clazz, String field ){
		Field f = null;
		try {
			f = clazz.getDeclaredField(field);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return f.getType().getSimpleName();
	}
	/**
	 * 
	 */
	public static String getFieldValue(Object obj, String method ){
		Method m = null;
		String fv = null;
		try {
			m = obj.getClass().getMethod(method);
			fv = (String)m.invoke(obj);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return fv;
	}
}
