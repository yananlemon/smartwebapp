package com.lemon.smartwebframework.core.init;

import com.lemon.smartwebframework.util.PropertiesHelper;

public final class SystemInit {

	public static void init() throws ClassNotFoundException {
		Class<?>[] clsList = {PropertiesHelper.class,ClassHelper.class,BeanHelper.class,IOCHelper.class,ControllerHelper.class};
		for (int i = 0; i < clsList.length; i++) {
			try {
				Class.forName(clsList[i].getName());
			} catch (ClassNotFoundException e) {
				throw e;
			}
		}
	}
	
}
