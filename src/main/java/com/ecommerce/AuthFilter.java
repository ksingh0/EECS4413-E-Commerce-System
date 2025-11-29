package com.ecommerce;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        // Allow login + signup + public pages
        String path = request.getRequestURI();
        if (path.contains("signin") || path.contains("signup") || path.contains("forgot-password")) {
            chain.doFilter(req, res);
            return;
        }

        //  Check session
        if (request.getSession(false) == null) {
            response.setStatus(401);
            response.getWriter().write("{\"error\":\"Login required\"}");
            return;
        }

        chain.doFilter(req, res);
    }
}
