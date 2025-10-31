package com.ksingh.studentapp;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
@WebListener
public class ApiCallCounterListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext ctx = sce.getServletContext();
		ctx.setAttribute("apiCallCounter", 0);
	}
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// You can add code here if you need to do something when the application is stopped
	}
}