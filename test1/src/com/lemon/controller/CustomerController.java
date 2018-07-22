package com.lemon.controller;

import java.util.ArrayList;
import java.util.List;

import com.lemon.smartwebapp.entity.Customer;
import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.RequestMapping;
import com.lemon.smartwebframework.core.request.Param;
import com.lemon.smartwebframework.core.request.View;

@Controller("/customer")
public class CustomerController {

	public CustomerController() {
	
	}
	
	@RequestMapping(method = "get",path = "/welcome")
	public String redirectWelcome() {
		return "jsp/welcome.jsp";
	}
	
	/**
	 * 跳转到客户列表页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/customers")
	public View listCustomer(){
		List<Customer> customers = new ArrayList<Customer>();
		customers.add(new Customer(1, "yanan", "test", "135644418187", "yananzx@yeah.net", null));
		customers.add(new Customer(1, "yanan", "test", "135644418187", "yananzx@yeah.net", null));
		customers.add(new Customer(1, "yanan", "test", "135644418187", "yananzx@yeah.net", null));
		View v = new View("jsp/customer_list.jsp");
		v.addModel("customers", customers);
		return v;
	}
	
	
	/**
	 * 跳转到新增客户页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/preAdd")
	public View goAddCustomerPage(){
		View v = new View("jsp/customer_add.jsp");
		return v;
	}
	
	
	@RequestMapping(method = "post",path = "/customer_create")
	public View create(Param param){
		View v = new View("jsp/customer_add.jsp");
		return v;
	}
	
	
}
