package com.lemon.smartwebframework.core.init;

import com.lemon.smartwebframework.util.ReflectionUtil;

public class BeanHelper {

	//private static HashMap<Class<?>,Object> bean = new HashMap<Class<?>,Object>();
	
	/**
	 * 加载所有的Bean包括Controller和Service
	 */
	/*static {
		Set<Class<?>> allClass = ClassHelper.getAllControllerClass();
		System.out.println("controller:"+allClass);
		allClass.addAll(ClassHelper.getAllServiceClass());
		System.out.println("service:"+allClass);
		for(Class<?> cls : allClass) {
			Object obj = ReflectionUtil.newInstance(cls);
			System.out.println(obj);
			bean.put(cls, obj);
		}
	}*/
	
	public static Object getBean(Class<?> cls) {
		/*if(!bean.containsKey(key))
			throw new RuntimeException("不能获取"+key+"的实例");
		Set<Class<?>> controllerClass = ClassHelper.getAllControllerClass();
		return bean.get(key);*/
		Object obj = ReflectionUtil.newInstance(cls);
		return obj;
	}
	
	/*public static HashMap<Class<?>,Object> getBeanMap() {
		return bean;
	}*/

}
