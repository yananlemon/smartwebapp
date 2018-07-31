package com.lemon.smartwebframework.core.init;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.lemon.smartwebframework.core.Handler;
import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.RequestMapping;
import com.lemon.smartwebframework.core.request.Request;

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
			Controller controller = controllerClass.getAnnotation(Controller.class);
			Method[] methods = controllerClass.getDeclaredMethods();
			for(Method method : methods) {
				// 判断当前方法是否有RequetMapping注解
				if(method.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping rm = method.getAnnotation(RequestMapping.class);
					String path = rm.path();
					String methodType = rm.method();
					Request req = new Request(methodType, controller.value() + path);
					Handler handler = new Handler(controllerClass, method);
					ACTION_MAP.put(req, handler);
				}
				
			}
		}
	}

	public static Handler getHandler(String requestPath, String requestMethod) {
		return ACTION_MAP.get(new Request(requestMethod,requestPath));
	}
	
}
