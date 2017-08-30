package com.example.springcloud.consumer.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by shiwen on 2017/8/30.
 */

@RestController
@RequestMapping("consumer")
public class HelloController
{
    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private DiscoveryClient discoveryClient;

    public String getServiceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("spring-cloud-provider");
        if (list != null && !list.isEmpty() ) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String sayHello(@RequestParam String  name){
        //构建url
        String baseUrl = getServiceUrl();
        String url ="/provider/hello?name="+name;

        URI uri = Paths.get(baseUrl, url).toUri();



        //构造请求
        String hello = restTemplate.getForObject(baseUrl+url, String.class);
        return "receive message ==>"+hello;

    }

    @RequestMapping(value = "/hello1",method = RequestMethod.GET)
    public String sayHello1(@RequestParam String  name){
        //调用远程服务
        return "hello1";

    }

}
