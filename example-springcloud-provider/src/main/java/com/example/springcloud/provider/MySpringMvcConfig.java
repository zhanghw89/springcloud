package com.example.springcloud.provider;

import com.example.springcloud.provider.aop.ControllerAop;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //给spring mvc添加拦截器,只拦截/hello2的接口
        registry.addInterceptor(new ControllerAop()).addPathPatterns("/hello2");
    }
}