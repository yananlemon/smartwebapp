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
		
		try {
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream in = classloader.getResourceAsStream(fileName);
			properties = new Properties();
			properties.load(in);
		} catch (IOException e) {
			throw new RuntimeException("读取"+fileName+"出错");
		}
	}
	
	public static Properties getProperties() {
		return properties;
	}
	
}
