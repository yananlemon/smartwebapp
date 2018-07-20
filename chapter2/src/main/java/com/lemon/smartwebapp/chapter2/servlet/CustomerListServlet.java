package com.lemon.smartwebapp.chapter2.servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet("/customer_list")
public class CustomerListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerService service = new CustomerService();
		List<Customer> customers = service.listCustomer();
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/WEB-INF/jsp/customerList.jsp").forward(request, response);
	}

}
