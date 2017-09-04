package com.example.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
//@EnableEurekaClient
//@EnableCircuitBreaker
//@EnableZuulProxy
@EnableDiscoveryClient  //启用服务注册与发现
//@EnableFeignClients  //启用feign进行远程调用
public class ConsumerApplication {


    @LoadBalanced //开启客户端的负载均衡
    @Bean
    RestTemplate loadBalanced() {
        return new RestTemplate();
    }

    /**
     * 不开启客户端负载均衡的
     * @return
     */
    @Primary
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }




    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }

}