package org.apache.camel.learn.filters;

import org.apache.camel.learn.utility.PropertiesConfig;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        PropertiesConfig propertiesConfig = new PropertiesConfig();
        System.out.println("Current Environment: " + propertiesConfig.getEnvironment());

        String bearerToken = req.getHeader("Authorization");

        if (bearerToken == null || !bearerToken.startsWith("Bearer ")) {
            resp.setStatus(401);
            resp.getWriter().write("Bearer token is required");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
