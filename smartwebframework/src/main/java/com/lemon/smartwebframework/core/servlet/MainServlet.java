package com.lemon.smartwebframework.core.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.alibaba.fastjson.JSON;
import com.lemon.smartwebframework.constants.GlobalConstants;
import com.lemon.smartwebframework.core.Handler;
import com.lemon.smartwebframework.core.init.BeanHelper;
import com.lemon.smartwebframework.core.init.ControllerHelper;
import com.lemon.smartwebframework.core.init.IOCHelper;
import com.lemon.smartwebframework.core.init.SystemInit;
import com.lemon.smartwebframework.core.request.Data;
import com.lemon.smartwebframework.core.request.Param;
import com.lemon.smartwebframework.core.request.View;
import com.lemon.smartwebframework.util.PropertiesHelper;
import com.lemon.smartwebframework.util.ServletContextHelper;

/**
 * <code>MainServlet</code>基于Servlet3.1,实现了<code>HttpServlet</code>中下面的方法:
 * <ul>
 * 	<li><code>init</code>,对smartframeword框架进行必要的初始化工作.</li>
 * 	<li><code>service</code>,对客户端请求进行响应,并分发给相应的Controller.</li>
 * </ul>
 * @author yanan
 *
 */
@WebServlet(name = "MainServlet",urlPatterns = "/*",loadOnStartup = 0)
@MultipartConfig
public class MainServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7499357748380204630L;

	/**
	 * 动态注册{@code excludingViewPath}和{@code excludingResourcesPath}
	 * @param @servletContext
	 */
	private void registerServlet(ServletContext servletContext) {
		
		// 获取视图路径和静态资料路径
		String excludingViewPath = PropertiesHelper.getProperties().getProperty(GlobalConstants.PROP_KEY_DEFAULT_VIEW_FOLDER);
		String excludingResourcesPath = PropertiesHelper.getProperties().getProperty(GlobalConstants.PROP_KEY_DEFAULT_STATIC_FOLDER);
		if(excludingViewPath == null || excludingViewPath.equals("" )
				|| excludingResourcesPath == null || excludingResourcesPath.equals("")){
			String msg = String.format("%s或者%s不能为空。", GlobalConstants.PROP_KEY_DEFAULT_VIEW_FOLDER,GlobalConstants.PROP_KEY_DEFAULT_STATIC_FOLDER);
			throw new RuntimeException(msg);
		}
		// 将这些路径添加到Servlet映射，MainServlet就会排除掉对这些路径的过滤
		ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(excludingViewPath + "*");
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(excludingResourcesPath + "*");
    }
	
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取请求方法和请求路径
		request.setCharacterEncoding("UTF-8");
		String contextPath    = request.getContextPath();
		String requestPath    = request.getRequestURI();
		requestPath = requestPath.substring(contextPath.length(), requestPath.length());
		String requestMethod  = request.getMethod().toLowerCase(); // get,post,put,delete
		if(!requestMethod.equals("get") && !requestMethod.equals("post")){
			// 不支持的请求类型目前只支持get post
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}else{
			Handler handler = ControllerHelper.getHandler(requestPath,requestMethod);
			if(handler != null) {
				try {
					
					// 创建请求参数对象
					HashMap<String,Object> parameterMap = new HashMap<String, Object>();
					Enumeration<String> parameters = request.getParameterNames();
					while(parameters.hasMoreElements()) {
						String key = parameters.nextElement();
						parameterMap.put(key, request.getParameter(key));
					}
					
					
					/**
					 * 解决线程安全问题，针对每个请求都生成一个新的Controller实例和新的Service实例
					 */
					Object obj = BeanHelper.getBean(handler.getControllerClass());
					IOCHelper.autoInject(obj);
					Object result = null;
					if(handler.getMethod().getParameterCount() == 0){
						result = handler.getMethod().invoke(obj);
					}else{
						String contentType = request.getContentType();
						Collection<Part> parts = null;
						Collection<Part> parts2 = new ArrayList<Part>();
						
						// 根据表单enctype类型封装参数
						if(contentType.toLowerCase().startsWith("multipart")){
							parts = request.getParts();
						
							// 遍历表单元素如果存在文件则添加到parts2中
							for (Part part : parts) {
								if(part.getSubmittedFileName() != null && !part.getSubmittedFileName().equals(""))
									parts2.add(part);
							}
						}
						Param param = new Param(parameterMap,parts2);
						result = handler.getMethod().invoke(obj, param);
					}
					
					// 处理Action返回值
					if(result instanceof View) {
						processView(request, response, contextPath, result);
					}
					else if(result instanceof Data) {
						processJSON(response, result);
					}
					
				} catch (Exception e) {
					response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				}
			}else{
				// 没有相应的handler处理请求
				response.sendError(HttpServletResponse.SC_NOT_FOUND);
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
		String basePath = PropertiesHelper.getProperties().getProperty(GlobalConstants.PROP_KEY_DEFAULT_VIEW_FOLDER);
		View view = (View) result;
		String path = view.getPath();
		if(path.startsWith("/")) {
			response.sendRedirect(contextPath + path);
		}else {
			Map<String,Object> model = view.getModel();
			for(Map.Entry<String, Object>entry : model.entrySet()) {
				request.setAttribute(entry.getKey(), entry.getValue());
			}
			request.getRequestDispatcher(basePath + path).forward(request, response);
			
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
		 try {
			SystemInit.init();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 ServletContextHelper.contextPath = servletContext.getContextPath();
		 ServletContextHelper.realPath = servletContext.getRealPath("/");
		 
	}
	
}
