package com.lemon.smartwebframework.util;
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
	
}
