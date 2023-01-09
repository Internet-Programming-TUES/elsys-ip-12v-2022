package org.elsys.ip.springdatajpa.web;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class OurFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("Hi from filter");
        ((HttpServletResponse)servletResponse).addHeader("Custom", "Custom");

        String queryString = ((HttpServletRequest) servletRequest).getQueryString();
        if (queryString == null || !queryString.contains("unlock=1914")) {
            ((HttpServletResponse)servletResponse).setStatus(402);
        }
        else {
            filterChain.doFilter(servletRequest, servletResponse);
            int a = 5;
        }


    }
}
