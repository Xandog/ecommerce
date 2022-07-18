package com.ecommerce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ecommerce.connection.DBConnection;
import com.ecommerce.dao.UserDao;
import com.ecommerce.model.User;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.sendRedirect("login.jsp");
//	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		System.out.print("initial connection");
		try(PrintWriter out = response.getWriter()) {
			String email = request.getParameter("login-email");
			String password = request.getParameter("login-password");
			out.print("in the LoginServlet, Login info: " + email + password);
			
			try {
				System.out.print("before connection");
				UserDao ud = new UserDao(DBConnection.getConnection());
				System.out.print("after connection");
				User user = ud.userLogin(email, password);
				
				if(user != null) {
					out.print("user login successful");
					request.getSession().setAttribute("auth", user);
					response.sendRedirect("index.jsp");
				} else {
					out.print("user login failed");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
