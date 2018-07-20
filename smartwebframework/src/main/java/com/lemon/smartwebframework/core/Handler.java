package com.lemon.smartwebframework.core;

import java.lang.reflect.Method;

public class Handler {

	/**Controller类对象**/
	private Class<?> controllerClass;
	
	private Method method;
	
	public Handler(Class<?> controllerClass, Method method) {
		this.controllerClass = controllerClass;
		this.method = method;
	}

	public Class<?> getControllerClass() {
		return controllerClass;
	}

	public Method getMethod() {
		return method;
	}
	
	
}
