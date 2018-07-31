package com.lemon.smartwebframework.util;

import java.lang.reflect.Field;

/**
 * 反射工具类
 * @author yanan
 *
 */
public class ReflectionUtil {

	/**
	 * 根据{@code cls}生成一个新的实例
	 * @param cls
	 * @return 指定Class类型的一个新的实例
	 * @throws InstantiationException 
	 * @throws IllegalAccessException 
	 */
	public static Object newInstance(Class<?> cls) throws InstantiationException, IllegalAccessException {
		Object instance = null;
		try {
			instance = cls.newInstance();
		} catch (InstantiationException e) {
			throw e;
		} catch (IllegalAccessException e) {
			throw e;
		}
		return instance;
	}

	/**
	 * 在指定的对象{@code obj}上给指定的字段{@code field}设置值{@code value}
	 * @param obj
	 * @param field
	 * @param value
	 * @throws Exception 
	 */
	public static void setField(Object obj, Field field, Object value) throws Exception {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			throw e;
		}
	}
	
}
