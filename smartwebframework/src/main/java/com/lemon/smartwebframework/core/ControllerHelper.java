package com.lemon.smartwebframework.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.RequestMapping;

/**
 * 控制器帮助类
 * @author yanan
 *
 */
public class ControllerHelper {
	
	/**
	 * Key	: Request
	 * Value: Handler
	 */
	private static final HashMap<Request,Handler> ACTION_MAP = new HashMap<Request, Handler>();
	
	static {
		// 获取所有Controller类
		Set<Class<?>> controllerSet = ClassHelper.getAllControllerClass();
		
		// 获取Controller类中所有RequestMapping方法
		Iterator<Class<?>> it = controllerSet.iterator();
		while(it.hasNext()) {
			Class<?> controllerClass = it.next();
			// 获取
			Controller controller = controllerClass.getAnnotation(Controller.class);
			Method[] methods = controllerClass.getDeclaredMethods();
			for(Method method : methods) {
				// 判断当前方法是否有RequetMapping标记
				if(method.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					String path = rm.path();
					String methodType = rm.method();
					Request req = new Request(methodType, controller.value() + path);
					Handler handler = new Handler(controllerClass, method);
					System.out.println("加载"+req);
					ACTION_MAP.put(req, handler);
				}
				
			}
		}
	}

	public static Handler getHandler(String requestPath, String requestMethod) {
		return ACTION_MAP.get(new Request(requestMethod,requestPath));
	}
	
}
