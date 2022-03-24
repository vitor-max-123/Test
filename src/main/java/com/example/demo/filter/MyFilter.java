package com.example.demo.filter;


import com.example.demo.exception.HttpParameterWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @create 2021/6/8 0008 21:19
 */
@WebFilter(filterName = "myFilter", urlPatterns = "/*")
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
