package com.lemon.smartwebframework.core.init;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import com.lemon.smartwebframework.constants.GlobalConstants;
import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.DataAccessLayer;
import com.lemon.smartwebframework.core.annotation.Service;
import com.lemon.smartwebframework.util.PropertiesHelper;

/**
 * 类加载工具类
 * @author yanan
 *
 */
public class ClassHelper {
	
	// 存储Controller && Service && Interface包中所有Class对象
	private static final Set<Class<?>> CLASS_SET; 
	
	/**
	 * 加载web应用中Controller && Service && Interface
	 */
	static {
		try {
			
			// 目前只加载指定包下所有类
			String controllerPackage = PropertiesHelper.getProperty(GlobalConstants.PROP_KEY_BASE_CONTROLLER_PATH);
			String servicePackage = PropertiesHelper.getProperty(GlobalConstants.PROP_KEY_BASE_SERVICE_PATH);
			String interfacePackage = PropertiesHelper.getProperty(GlobalConstants.PROP_KEY_BASE_SERVICE_INTERFACE_PATH);
			String ormInterfacePackage = PropertiesHelper.getProperty(GlobalConstants.PROP_KEY_BASE_ORM_INTERFACE_PATH);
			String[] classArray = {controllerPackage,servicePackage,interfacePackage,ormInterfacePackage};
			Set<Class<?>> sets = new HashSet<Class<?>>();
			ClassLoader loader = getClassLoader();
			for (int i = 0; i < classArray.length; i++) {
				
				// 获取指定名称的所有资源
				Enumeration<URL> urls = loader.getResources(classArray[i].replace(".", "/"));
				while(urls.hasMoreElements()) {
					URL url = urls.nextElement();
					if(url == null)
						continue;
					if(url.getProtocol().equals("file")) {
						String packagePath = url.getPath().replaceAll("%20", "");
						addClass(sets, packagePath, classArray[i]);
					}
				}
			}
			CLASS_SET = sets;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 过滤并加载class文件
	 * @param sets
	 * @param packagePath
	 * @param packageName
	 */
	private static void addClass(Set<Class<?>> sets,String packagePath,String packageName) {
		
		File[] files = new File(packagePath).listFiles(new FileFilter() {
			
			public boolean accept(File file) {
				return (file.isFile() && file.getName().endsWith(".class")) || file.isDirectory();
			}
		});
		
		for (File file : files) {
			String fileName = file.getName();
			if(file.isFile()) {
				String className = fileName.substring(0, fileName.indexOf("."));
				if(packageName != null && !packageName.equals(""))
					className = packageName + "." + className;
				doAddClass(sets,className);
			}
		}
	}
	
	
	private static void doAddClass(Set<Class<?>> sets, String className) {
		Class<?> cls;
		try {
			cls = Class.forName(className, false, getClassLoader());
			sets.add(cls);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取类加载器
	 * @return ClassLoader
	 */
	static ClassLoader getClassLoader() {
		return Thread.currentThread().getContextClassLoader();
	}
	
	/**
	 * 获取web应用中所有打过Controller标签的类
	 * @return
	 */
	public static Set<Class<?>> getAllControllerClass(){
		Set<Class<?>> rs = new HashSet<Class<?>>();
		for(Class<?> c : CLASS_SET) {
			if(c.isAnnotationPresent(Controller.class))
				rs.add(c);
		}
		return rs;
	}
	
	/**
	 * 获取web应用中所有打过Service标签的类
	 * @return
	 */
	public static Set<Class<?>> getAllServiceClass(){
		Set<Class<?>> rs = new HashSet<Class<?>>();
		for(Class<?> c : CLASS_SET) {
			if(c.isAnnotationPresent(Service.class))
				rs.add(c);
		}
		return rs;
	}
	
	/**
	 * 获取web应用中所有打过DataAccessLayer标签的类
	 * @return
	 */
	public static Set<Class<?>> getAllDataAccessLayerClass(){
		Set<Class<?>> rs = new HashSet<Class<?>>();
		for(Class<?> c : CLASS_SET) {
			if(c.isAnnotationPresent(DataAccessLayer.class))
				rs.add(c);
		}
		return rs;
	}
	
	/**
	 * 获取web应用中所有打过Service标签的类
	 * @return
	 */
	public static Set<Class<?>> getAllServiceAndInterfaceClass(){
		Set<Class<?>> rs = new HashSet<Class<?>>();
		for(Class<?> c : CLASS_SET) {
			if(c.isAnnotationPresent(Service.class))
				rs.add(c);
		}
		return rs;
	}
	
	public static Set<Class<?>> getAllClass(){
		return CLASS_SET;
	}
	
}
