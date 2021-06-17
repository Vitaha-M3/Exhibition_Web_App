package com.exhibitions.controller.filter;

import com.exhibitions.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        User user = (User) request.getSession().getAttribute("user");
        String[] uri = request.getRequestURI().split("/");
        if(access(user, uri[uri.length - 1])) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("/errors/accessfail.jsp");
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean access(User user, String page) {
        return user != null && page.contains(user.getAccess().toString().toLowerCase());
    }
}
