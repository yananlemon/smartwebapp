package com.lemon.controller;

import com.lemon.entity.CustomerGroup;
import com.lemon.service.ICustomerGroupService;
import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.Inject;
import com.lemon.smartwebframework.core.annotation.RequestMapping;
import com.lemon.smartwebframework.core.request.Data;
import com.lemon.smartwebframework.core.request.Param;

@Controller("/customergroup")
public class CustomerGroupController {

	
	@Inject
	private ICustomerGroupService service;
	
	
	
	/**
	 * 跳转到客户列表页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/getCustomer")
	public Data getCustomerGroup(Param param){
		long id = Long.parseLong(param.getParametermap().get("id").toString());
		CustomerGroup group = service.getById(id);
		Data data = new Data(group);
		return data;
	}
	
	
}