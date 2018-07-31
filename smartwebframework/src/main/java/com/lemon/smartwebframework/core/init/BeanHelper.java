package com.lemon.smartwebframework.core.init;

import java.util.Set;

import com.lemon.smartwebframework.util.ReflectionUtil;

public class BeanHelper {
	
	final static Set<Class<?>> allCls ;
	
	static{
		allCls = ClassHelper.getAllClass();
	}
	
	/**
	 * 获取指定的{@code cls}的类对象的一个新的实例
	 * @param cls
	 * @return 指定的{@code cls}的类对象的一个新的实例
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public static Object getBean(Class<?> cls) throws InstantiationException, IllegalAccessException {
		if(!allCls.contains(cls))
			throw new RuntimeException("不能获取"+cls+"的实例");
		Object obj = ReflectionUtil.newInstance(cls);
		return obj;
	}
}
