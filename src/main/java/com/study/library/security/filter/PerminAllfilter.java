package com.study.library.security.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class PerminAllfilter extends GenericFilter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        List<String> antMatchers = List.of("/error", "/server", "/auth", "/mail/authenticate");
        String url = request.getRequestURI();
        request.setAttribute("isPermitAll", false);
        System.out.println(url);
        for(String antMatcher : antMatchers) {
            if(url.contains(antMatcher)) {
                request.setAttribute("isPermitAll", true);
            }
        }
        filterChain.doFilter(request, response);
    }
}
