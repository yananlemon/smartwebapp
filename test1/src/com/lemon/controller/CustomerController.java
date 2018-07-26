package com.lemon.controller;

import java.util.ArrayList;
import java.util.List;

import com.lemon.entity.Customer;
import com.lemon.service.ICustomerService;
import com.lemon.smartwebframework.core.annotation.Controller;
import com.lemon.smartwebframework.core.annotation.Inject;
import com.lemon.smartwebframework.core.annotation.RequestMapping;
import com.lemon.smartwebframework.core.request.Data;
import com.lemon.smartwebframework.core.request.Param;
import com.lemon.smartwebframework.core.request.View;

@Controller("/customer")
public class CustomerController {

	private int count = 0;
	
	@Inject
	private ICustomerService service;
	
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
	 * 跳转到客户列表页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/add")
	public Data increaseCount(){
		count++;
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Data data = new Data(count);
		return data;
	}
	
	/**
	 * 跳转到客户列表页面
	 * @return
	 */
	@RequestMapping(method = "get",path = "/add2")
	public Data increaseCount2(){
		int a = service.increase();
		Data data = new Data(a);
		return data;
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