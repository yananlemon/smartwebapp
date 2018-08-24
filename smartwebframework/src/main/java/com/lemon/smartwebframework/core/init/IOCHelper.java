package com.lemon.smartwebframework.core.init;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.lemon.smartwebframework.core.annotation.Inject;
import com.lemon.smartwebframework.plugin.orm.session.ISqlSession;
import com.lemon.smartwebframework.plugin.orm.session.ISqlSessionFactory;
import com.lemon.smartwebframework.plugin.orm.session.SqlSessionFactoryBuilder;
import com.lemon.smartwebframework.util.ReflectionUtil;

/**
 * 依赖注入帮助类
 * @author yanan
 *
 */
public class IOCHelper {
	
	
	private static final HashMap<Class<?>,Class<?>> SERVICE_MAP = new HashMap<Class<?>,Class<?>>();
	
	/**
	 * 如果指定的{@code obj}对象中有{@link com.lemon.smartwebframework.core.annotation.Inject}
	 * 注解的字段则初始化一个新的实例
	 * @param obj
	 * @throws Exception 
	 */
	public static void autoInject(Object obj) throws Exception{
		Class<?> cls = obj.getClass();
		Field[] fs = cls.getDeclaredFields();
		Set<Class<?>> serviceSet = ClassHelper.getAllServiceClass();
		for (Field f : fs) {
			// 如果该字段有Inject标记并且其类型是接口
			if(f.isAnnotationPresent(Inject.class) && f.getType().isInterface()) {
				
				// 遍历Service类集合，寻找该接口的实现类，如果找到则设置obj中引用该接口的实例字段的值为该实现类的实例
				boolean notFound = true;
				for (Iterator<Class<?>> iterator = serviceSet.iterator(); iterator.hasNext();) {
					Class<?> serviceClass = iterator.next();
					if(f.getType().isAssignableFrom(serviceClass) && !serviceClass.equals(f.getType())) {
						Object fieldInstance = ReflectionUtil.newInstance(serviceClass);
						ReflectionUtil.setField(obj,f,fieldInstance);
						// 实例化Service中需要注入的dao层对象
						settingsMapperInstance(fieldInstance);
						notFound = false;
					}
				}
				if(notFound){
					throw new RuntimeException("不能实例化"+f.getName());
				}
				
			}
		}
	}
	
	private static void settingsMapperInstance(Object obj) throws Exception{
		Class<?> cls = obj.getClass();
		Field[] fs = cls.getDeclaredFields();
		for (Field f : fs) {
			// 如果该字段有Inject标记并且其类型是接口
			if(f.isAnnotationPresent(Inject.class) && f.getType().isInterface()) {
				InputStream	inputStream =  IOCHelper.class.getClassLoader().getResourceAsStream("simulation_mybatis_config.xml");  
				ISqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);  
				ISqlSession session = sqlSessionFactory.openSession();  
				Object value = session.getMapper(f.getType());
				f.setAccessible(true);
				f.set(obj, value);
			}
		}
	}
	
	
	public static void main(String[] args) {
		System.out.println(SERVICE_MAP);
	}
}