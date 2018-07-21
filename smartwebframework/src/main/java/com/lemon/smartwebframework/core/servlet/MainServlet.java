package com.lemon.smartwebframework.core.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.smartwebframework.core.ControllerHelper;
import com.lemon.smartwebframework.core.Handler;
import com.lemon.smartwebframework.util.ReflectionUtil;

/**
 * MainServlet
 * @author yanan
 *
 */
@WebServlet(name = "MainServlet",urlPatterns = "/*",loadOnStartup = 0)
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7499357748380204630L;

	private void registerServlet(ServletContext servletContext) {
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping("/index.jsp");
        jspServlet.addMapping("/WEB-INF/jsp/*");

        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping("/favicon.ico");
        defaultServlet.addMapping("/WEB-INF/asset*");
    }	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求方法和请求路径
		//String requestPath    = request.getPathInfo();// /customer/welcome
		String contextPath    = request.getContextPath();
		String requestPath    = request.getRequestURI();
		requestPath = requestPath.substring(contextPath.length(), requestPath.length());
		String requestMethod  = request.getMethod().toLowerCase(); // get,post,put,delete
		System.out.println("requestPath:"+requestPath);
		Handler handler = ControllerHelper.getHandler(requestPath,requestMethod);
		if(handler != null) {
			try {
				System.out.println("requestPath"+requestPath+"requestMethod:"+requestMethod);
				// 创建请求参数对象
				HashMap<String,Object> parameterMap = new HashMap<String, Object>();
				Enumeration<String> parameters = request.getAttributeNames();
				while(parameters.hasMoreElements()) {
					String key = parameters.nextElement();
					parameterMap.put(key, request.getParameter(key));
				}
				Object obj = ReflectionUtil.newInstance(handler.getControllerClass());
				Object result = null;
				if(parameterMap.size() <= 0) {
					result = handler.getMethod().invoke(obj);
				}else {
					result = handler.getMethod().invoke(obj, parameterMap);
				}
				if(result instanceof String) {
					System.out.println(contextPath + result.toString());
					request.getRequestDispatcher("/WEB-INF/"+result.toString()).forward(request, response);
				}
					
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}else {
			//request.getRequestDispatcher(requestPath).forward(request, response);
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	/**
	 * 初始化系统中所有标记为Controller的类及其Action方法
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		 ServletContext servletContext = servletConfig.getServletContext();

	        registerServlet(servletContext);
	}
	
	
}
