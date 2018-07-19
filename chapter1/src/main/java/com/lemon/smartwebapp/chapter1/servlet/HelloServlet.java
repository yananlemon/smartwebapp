package com.lemon.smartwebapp.chapter1.servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by andy on 2018/7/19.
 */
@WebServlet(name = "HelloServlet",urlPatterns = "/HelloServlet")
public class HelloServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6258366690408577293L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Date date = new Date();
        SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = format.format(date);
        request.setAttribute("currentTime",nowTime);
        request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp").forward(request,response);
    }
}