package com.lemon.smartwebframework.core.init;

import java.util.HashMap;
import java.util.Set;

import com.lemon.smartwebframework.util.ReflectionUtil;

public class BeanHelper {

	private static HashMap<Class<?>,Object> bean = new HashMap<Class<?>,Object>();
	
	/**
	 * 加载所有的Bean包括Controller和Service
	 */
	static {
		Set<Class<?>> allClass = ClassHelper.getAllControllerClass();
		System.out.println("controller:"+allClass);
		allClass.addAll(ClassHelper.getAllServiceClass());
		System.out.println("service:"+allClass);
		for(Class<?> cls : allClass) {
			Object obj = ReflectionUtil.newInstance(cls);
			System.out.println(obj);
			bean.put(cls, obj);
		}
	}
	
	public static Object getBean(Class<?> key) {
		if(!bean.containsKey(key))
			throw new RuntimeException("不能获取"+key+"的实例");
		return bean.get(key);
	}
	
	public static HashMap<Class<?>,Object> getBeanMap() {
		return bean;
	}

}
