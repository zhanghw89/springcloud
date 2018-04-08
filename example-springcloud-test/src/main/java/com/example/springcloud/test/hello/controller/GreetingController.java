package com.example.springcloud.test.hello.controller;

import com.example.springcloud.test.hello.model.Person;
import com.example.springcloud.test.hello.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

@Controller
public class GreetingController {

    @Autowired
    private GreetingService greetingService;

    /**
     * 注意 @ResponseBody注解
     *
     * @param name 姓名
     * @return
     */
    @GetMapping("greeting")
    @ResponseBody
    String greeting(String name) {
        Assert.notNull(name);
        return greetingService.sayHello(name);
    }

    @GetMapping("ageIncrease")
    @ResponseBody
    Person ageIncrease(@RequestBody Person user) {
        Assert.notNull(user);
        Assert.notNull(user.getName());
        Person result = greetingService.addAge(user);
        System.out.println("user=" + user);
        System.out.println("result=" + result);
        return result;
    }


    @PostMapping("testPost")
    @ResponseBody
    Person testPost(@RequestBody Person user, @RequestBody Person user1) {
        Assert.notNull(user.getName());
        Assert.notNull(user1.getName());
        Person result = greetingService.addAge(user);
        System.out.println("user=" + user);
        System.out.println("result=" + result);
        return result;
    }


}