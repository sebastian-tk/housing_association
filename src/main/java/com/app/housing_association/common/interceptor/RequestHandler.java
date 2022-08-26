package com.app.housing_association.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestHandler implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("Request received with method: {}, path URI: {}, parameters: {}, complete path: {}"
                , request.getMethod(), request.getRequestURI(), request.getQueryString(), request.getRequestURL());
        return true;
    }
}
