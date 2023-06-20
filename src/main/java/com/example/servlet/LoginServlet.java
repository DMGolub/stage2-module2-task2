package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		Object user = request.getSession().getAttribute("user");
		if (user == null) {
			response.sendRedirect("/login.jsp");
		} else {
			response.sendRedirect("/user/hello.jsp");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		String user = (String) request.getAttribute("user");
		String password = (String) request.getAttribute("password");
		boolean userIsValid = user != null && !user.isEmpty() &&
			Users.getInstance().getUsers().contains(user);
		boolean passwordIsValid = password != null && !password.isEmpty();
		if (userIsValid && passwordIsValid) {
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/user/hello.jsp");
		} else {
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}
}