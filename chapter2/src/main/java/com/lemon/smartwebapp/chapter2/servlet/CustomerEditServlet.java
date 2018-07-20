package com.lemon.smartwebapp.chapter2.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.smartwebapp.chapter2.entity.Customer;
import com.lemon.smartwebapp.chapter2.service.CustomerService;


@WebServlet("/customer_edit")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Enumeration<String> keys = req.getParameterNames();
		while(keys.hasMoreElements()){
			System.out.println(keys.nextElement());
		}
		CustomerService service = new CustomerService();
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String remark = req.getParameter("remark");
		Customer customer = new Customer(id,name, contact, telephone, email, remark);
		int result = service.updateCustomer(customer);
		if(result == 1){
			req.setAttribute("flag", "1");
			resp.sendRedirect("/customer_list");
		}else{
			resp.sendRedirect("/WEB-INF/jsp/500.jsp");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CustomerService service = new CustomerService();
		long id = Long.parseLong(req.getParameter("id"));
		String name = req.getParameter("name");
		String contact = req.getParameter("contact");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String remark = req.getParameter("remark");
		Customer customer = new Customer(id,name, contact, telephone, email, remark);
		int result = service.updateCustomer(customer);
		if(result == 1){
			req.setAttribute("flag", "1");
			resp.sendRedirect("/customer_list");
		}else{
			resp.sendRedirect("/WEB-INF/jsp/500.jsp");
		}
	}

	

}
