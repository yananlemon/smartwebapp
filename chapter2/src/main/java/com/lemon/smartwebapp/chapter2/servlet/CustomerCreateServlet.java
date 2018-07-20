package com.lemon.smartwebapp.chapter2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.smartwebapp.chapter2.entity.Customer;
import com.lemon.smartwebapp.chapter2.service.CustomerService;

/**
 * Servlet implementation class CustomerCreateServlet
 */
@WebServlet("/customer_create")
public class CustomerCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerCreateServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService service = new CustomerService();
		String name = request.getParameter("name");
		String contact = request.getParameter("contact");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String remark = request.getParameter("remark");
		Customer customer = new Customer(name, contact, telephone, email, remark);
		int result = service.createCustomer(customer);
		if(result == 1){
			request.setAttribute("flag", "1");
			response.sendRedirect("customer_list");
		}else{
			response.sendRedirect("/WEB-INF/jsp/500.jsp");
		}
	}

}
