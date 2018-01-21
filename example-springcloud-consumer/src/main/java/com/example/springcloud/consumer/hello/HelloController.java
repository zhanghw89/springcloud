package com.example.springcloud.consumer.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by shiwen on 2017/8/30.
 */

@RestController
public class HelloController {

    //非负载均衡的
    @Autowired
    private RestTemplate restTemplate;

    //负载均衡使用这个
    @Autowired
    @LoadBalanced
    private RestTemplate loadBlanceRestTemplate;


    @Autowired
    private DiscoveryClient discoveryClient;

    public String getServiceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("provider");
        if (list != null && !list.isEmpty()) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

    @RequestMapping
    public String success() {
        return "success!";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello(@RequestParam String name) {
        //构建url
        String baseUrl = getServiceUrl();
        String url = "/hello?name=" + name;
        String fullUrl = baseUrl + url;
        //构造请求
        String hello = restTemplate.getForEntity(fullUrl, String.class).getBody();
        return "receive message ==>" + hello;

    }


}
