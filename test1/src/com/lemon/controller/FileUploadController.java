package com.lemon.controller;

import java.util.Collection;

import javax.servlet.http.Part;

import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.RequestMapping;
import com.lemon.smartwebframework.core.request.Param;
import com.lemon.smartwebframework.core.request.View;
import com.lemon.smartwebframework.util.ServletContextHelper;

@Controller("/files")
public class FileUploadController {

	/**
	 * 跳转到新增客户页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/preAdd")
	public View goAddCustomerPage(){
		View v = new View("files_upload.jsp");
		return v;
	}

	/**
	 * 跳转到新增客户页面
	 * @return
	 */
	@RequestMapping(method = "post",path = "/customer")
	public View create(Param param){
		System.out.println(param);
		View v = new View("files_upload.jsp");
		Collection<Part> parts = param.getParts();
		if(parts != null && parts.size() >= 0){
			for (Part part : parts) {
				try {
					if (part != null) {
						String submittedFileName = part.getSubmittedFileName();// 原文件名称，Servlet3.1提供
						if (submittedFileName != null && part.getSize() > 0) {
							// 获取上传文件信息
							System.out.println("文件类型：" + part.getContentType());// MIME类型
							System.out.println("文件大小：" + part.getSize());// 文件大小 字节
							System.out.println("SubmittedFileName：" + part.getSubmittedFileName());
							System.out.println("Name：" + part.getName());

							// 获取文件上传域信息
							Collection<String> headerNames = part.getHeaderNames();
							for (String headName : headerNames) {
								System.out.println("headName：" + headName + " --- value：" + part.getHeader(headName));
							}

							// 保存至服务器
							String basePath = ServletContextHelper.realPath;
							String path = basePath + "/uploads/file/" + submittedFileName;
							part.write(path);
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return v;
	}
}
