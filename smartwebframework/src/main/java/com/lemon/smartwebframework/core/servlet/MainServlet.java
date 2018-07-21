package com.lemon.smartwebframework.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.lemon.smartwebframework.core.Handler;
import com.lemon.smartwebframework.core.init.ControllerHelper;
import com.lemon.smartwebframework.core.request.Data;
import com.lemon.smartwebframework.core.request.Param;
import com.lemon.smartwebframework.core.request.View;
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
		request.setCharacterEncoding("UTF-8");
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
				Enumeration<String> parameters = request.getParameterNames();
				while(parameters.hasMoreElements()) {
					String key = parameters.nextElement();
					parameterMap.put(key, request.getParameter(key));
				}
				Object obj = ReflectionUtil.newInstance(handler.getControllerClass());
				Object result = null;
				if(parameterMap.size() <= 0) {
					result = handler.getMethod().invoke(obj);
				}else {
					Param param = new Param(parameterMap);
					result = handler.getMethod().invoke(obj, param);
				}
				// 处理Action返回值
				if(result instanceof View) {
					processView(request, response, contextPath, result);
				}
				else if(result instanceof Data) {
					processJSON(response, result);
				}
					
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 处理JSON
	 * @param response
	 * @param result
	 * @throws IOException
	 */
	private void processJSON(HttpServletResponse response, Object result) throws IOException {
		Data data = (Data) result;
		Object model = data.getObject();
		if(model != null) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter writer = response.getWriter();
			writer.write(JSON.toJSONString(model));
			writer.flush();
			writer.close();
		}
	}
	/**
	 * 处理JSP页面
	 * @param request
	 * @param response
	 * @param contextPath
	 * @param result
	 * @throws IOException
	 * @throws ServletException
	 */
	private void processView(HttpServletRequest request, HttpServletResponse response, String contextPath,
			Object result) throws IOException, ServletException {
		View view = (View) result;
		String path = view.getPath();
		if(path.startsWith("/")) {
			response.sendRedirect(contextPath + path);
		}else {
			Map<String,Object> model = view.getModel();
			for(Map.Entry<String, Object>entry : model.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			request.getRequestDispatcher("/WEB-INF/"+path).forward(request, response);
			
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	/**
	 * 初始化
	 */
	public void init(ServletConfig servletConfig) throws ServletException {
		 ServletContext servletContext = servletConfig.getServletContext();
		 registerServlet(servletContext);
	}
	
}
