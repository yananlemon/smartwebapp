package com.lemon.smartwebframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 资源文件工具类
 * @author yanan
 *
 */
public class PropertiesHelper {

	static Properties properties = null;
	
	static final String fileName = "smartframework.properties";
	
	static {
		
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream in = classloader.getResourceAsStream(fileName);
			properties = new Properties();
			try {
				properties.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public static Properties getProperties() {
		return properties;
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
}
