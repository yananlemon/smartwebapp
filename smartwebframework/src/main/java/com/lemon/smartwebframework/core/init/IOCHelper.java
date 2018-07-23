package com.lemon.smartwebframework.core.init;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.lemon.smartwebframework.core.annotation.Inject;
import com.lemon.smartwebframework.util.ReflectionUtil;

/**
 * 依赖注入帮助类
 * @author yanan
 *
 */
public class IOCHelper {
	
	
	private static final HashMap<Class<?>,Class<?>> SERVICE_MAP = new HashMap<Class<?>,Class<?>>();
	
	static{
		HashMap<Class<?>,Object> beans = BeanHelper.getBeanMap();
		System.out.println("beans : "+beans);
		Iterator<Class<?>> it = beans.keySet().iterator();
		Set<Class<?>> serviceSet = ClassHelper.getAllServiceClass();
		while(it.hasNext()) {
			Class<?> cls = it.next();
			Object obj = beans.get(cls);
			Field[] fs = cls.getDeclaredFields();
			for (Field f : fs) {
				// 如果该字段有Inject标记并且其类型是接口
				if(f.isAnnotationPresent(Inject.class) && f.getType().isInterface()) {
					for (Iterator<Class<?>> iterator = serviceSet.iterator(); iterator.hasNext();) {
						Class<?> serviceClass = iterator.next();
						System.out.println(f.getType().isAssignableFrom(serviceClass));
						if(f.getType().isAssignableFrom(serviceClass) && !serviceClass.equals(f.getType())) {
							Object fieldInstance = ReflectionUtil.newInstance(serviceClass);
							System.out.println("field type :"+f.getType());
							ReflectionUtil.setField(obj,f,fieldInstance);
							System.out.println(obj.toString()+f.getName()+fieldInstance);
						}
					}
					
				}
			}
		}
		
	}
	
	
	
	public static void main(String[] args) {
		System.out.println(SERVICE_MAP);
	}
}