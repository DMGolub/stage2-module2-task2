package com.example.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*"})
public class AuthFilter implements Filter {

	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
		throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
 		Object user = httpServletRequest.getAttribute("user");
		if (user == null) {
			((HttpServletResponse) response).sendRedirect("/login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}
}