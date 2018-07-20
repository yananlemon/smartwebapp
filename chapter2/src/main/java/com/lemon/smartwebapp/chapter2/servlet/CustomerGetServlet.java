package com.lemon.smartwebapp.chapter2.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lemon.smartwebapp.chapter2.entity.Customer;
import com.lemon.smartwebapp.chapter2.service.CustomerService;


@WebServlet("/customer_get")
public class CustomerGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerGetServlet() {
        super();
    }

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CustomerService service = new CustomerService();
		long id = Long.parseLong(req.getParameter("id"));
		Customer customer = service.getCustomerById(id);
		if(customer != null){
			req.setAttribute("customer", customer);
			req.getRequestDispatcher("/WEB-INF/jsp/customerEdit.jsp").forward(req, resp);
		}else{
			resp.sendRedirect("/WEB-INF/jsp/500.jsp");
		}
	}

	

}
