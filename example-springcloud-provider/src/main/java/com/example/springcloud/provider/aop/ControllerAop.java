package com.example.springcloud.provider.aop;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

/**
 * @author shiwen
 * 设置http 跨域
 */
public class ControllerAop extends HandlerInterceptorAdapter {

    private static final Set<String> hosts = new HashSet<>();

    static {
        hosts.add("http://localhost:8080");
        hosts.add("http://ssss.cn:8080");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String host = request.getHeader("origin");

        if(hosts.contains(host)){
            response.setHeader("Access-Control-Allow-Origin",host);//允许的ip
            response.setHeader("Access-Control-Allow-Methods","POST"); //允许的请求类别
            response.setHeader("Access-Control-Max-Age","100");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
            response.setHeader("Access-Control-Allow-Credentials","true");
        }

        return super.preHandle(request, response, handler);
    }

}