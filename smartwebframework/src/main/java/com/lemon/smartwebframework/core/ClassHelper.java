package com.lemon.smartwebframework.core;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import com.lemon.smartwebframework.core.annotation.Controller;

/**
 * 类加载工具类
 * @author yanan
 *
 */
public class ClassHelper {
	
	private static final Set<Class<?>> CLASS_SET; 
	
	/**
	 * 加载web应用中所有类
	 */
	static {
		// 目前只加载指定包下所有类
		String controllerPackage = "com.lemon.smartwebapp.chapter";
		Set<Class<?>> sets = new HashSet<Class<?>>();
		ClassLoader loader = getClassLoader();
		try {
			// 获取指定名称的所有资源
			Enumeration<URL> urls = loader.getResources(controllerPackage.replace(".", "/"));
			
			// 遍历
			while(urls.hasMoreElements()) {
				URL url = urls.nextElement();
				if(url == null)
					continue;
				System.out.println(url.getProtocol());
				if(url.getProtocol().equals("file")) {
					String packagePath = url.getPath().replaceAll("%20", "");
					addClass(sets, packagePath, controllerPackage);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CLASS_SET = sets;
	}
	
	static void addClass(Set<Class<?>> sets,String packagePath,String packageName) {
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
	
	public static void main(String[] args) {
		ClassHelper helper = new ClassHelper();
		System.out.println(helper.CLASS_SET);
	}
	
	
}
