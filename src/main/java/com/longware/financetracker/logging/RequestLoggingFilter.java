package com.longware.financetracker.logging;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.java.Log;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@Log
public class RequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info("Request Info= " +
        "{" + req.getMethod() + "} " +
        "{" + req.getRequestURI() + "} " + 
        "{" + req.getProtocol() + "} " + 
        "{" + req.getAuthType() + "} " +
        "{" + req.getRemoteAddr() + "} " );
        Enumeration<String> headerNames = req.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            log.info("{" + headerName + ": " + headerValue + "} ");
        }


        
        chain.doFilter(request, response);
    }
}
