package com.ffl.blog.start.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lff
 * @datetime 2020/02/01 14:18
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    private static final String TOKEN_NAME = "accessToken";

    private static final String TOKEN_VALUE = "";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials","true");

        String method = request.getMethod();

        // 如果是 OPTIONS 则设置成ok
        if (method.equals("OPTIONS")){
            response.setStatus(200);
        }

        return super.preHandle(request, response, handler);
    }
}
