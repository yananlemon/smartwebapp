package com.lemon.smartwebframework.core;

import java.lang.reflect.Method;

public class Handler {

	private Class<?> controllerClass;	// Controller类对象
	
	private Method method;				// Controller类对象中method
	
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
