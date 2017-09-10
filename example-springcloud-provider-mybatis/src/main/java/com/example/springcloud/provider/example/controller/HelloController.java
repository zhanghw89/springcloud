package com.example.springcloud.provider.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    private static final Logger logger = LoggerFactory.getLogger(HelloController.class);


    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/")
    public String index() {
        ServiceInstance instance = client.getLocalServiceInstance();
        String hello = "hello,success!"+ "host="+instance.getHost()+",serviceId="+instance.getServiceId();
        logger.info(hello);
        return hello;
    }

    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {

        return "hello "+name+"，this is first messge";
    }
}