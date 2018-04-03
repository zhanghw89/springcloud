package com.example.springcloud.test.hello.service.impl;

import com.example.springcloud.test.hello.service.GreetingService;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : 张会文
 * @date : Created in 下午5:01 2018/4/3
 */
@Service
public class GreetingServiceImpl implements GreetingService{
    @Override
    public String sayHello(String name) {
        return "hello,"+name;
    }
}
