package com.lemon.smartwebframework.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.lemon.smartwebframework.constants.GlobalConstants;

/**
 * 资源文件工具类
 * @author yanan
 *
 */
public class PropertiesHelper {

	static Properties properties = null;
	static {
		
			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
			InputStream in = classloader.getResourceAsStream(GlobalConstants.FRAMEWORK_PROPERTIES_FILENAME);
			properties = new Properties();
			try {
				properties.load(in);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
	}
	
	public static Properties getProperties() {
		return properties;
	}
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
	
}
