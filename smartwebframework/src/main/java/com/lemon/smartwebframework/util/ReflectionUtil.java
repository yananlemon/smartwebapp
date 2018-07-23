package com.lemon.smartwebframework.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * @author yanan
 *
 */
public class ReflectionUtil {

	public static Object newInstance(Class<?> cls) {
		Object instance = null;
		try {
			instance = cls.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}

	public static void setField(Object obj, Field f, Object fieldInstance) {
		try {
			f.setAccessible(true);
			f.set(obj, fieldInstance);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
