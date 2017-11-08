package com.example.springcloud.provider.hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/")
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();
        String hello = "hello,success!" + "host=" + instance.getHost() + ",serviceId=" + instance.getServiceId();
        logger.info(hello);
        return hello;
    }



    @RequestMapping(value = "/hello2",method ={RequestMethod.POST})
    public String hello2(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
//        String result = callback + "(" +cookies[0].getName() + ":"+ cookies[0].getValue()+");";

//        String result = callback + "('" +cookies[0].getName() + ":"+ cookies[0].getValue()+"');";
        String result ="right";
        return result;
    }

    @RequestMapping(value = "/hello3",method ={RequestMethod.POST})
    public String hello3(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
//        String result = callback + "(" +cookies[0].getName() + ":"+ cookies[0].getValue()+");";

//        String result = callback + "('" +cookies[0].getName() + ":"+ cookies[0].getValue()+"');";
        String result ="right2";
        return result;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name, HttpServletRequest request, HttpServletResponse response) {
        response.addCookie(new Cookie("aaa","bbb"));
        return "console.log(0)";
    }
}