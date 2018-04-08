package com.example.springcloud.test.hello.service;

import com.example.springcloud.test.hello.model.Person;
import org.springframework.stereotype.Service;

/**
 * description :
 *
 * @author : 张会文
 * @date : Created in 下午5:00 2018/4/3
 */
public interface GreetingService {

    String sayHello(String name);


    Person addAge(Person user);


}
