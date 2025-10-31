package com.ksingh.studentapp;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
@SuppressWarnings("unused")
@WebFilter("/*")
public class ApiCallCounterFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		ServletContext ctx = request.getServletContext();
		Integer counter = (Integer) ctx.getAttribute("apiCallCounter");
		counter++;
		ctx.setAttribute("apiCallCounter", counter);
		chain.doFilter(request, response);
	}
	// Implement init and destroy methods if necessary
}