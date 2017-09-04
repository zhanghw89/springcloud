package com.example.springcloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Created by shiwen on 2017/8/31.
 */

@SpringBootApplication
@EnableConfigServer //开启配置服务器支持
@EnableEurekaClient  //开启Eureka的客户端支持
public class SpringCloudConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudConfigApplication.class,args);
    }
}
