package com.example.springcloud.test.hello.controller;

import com.example.springcloud.test.hello.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return greetingService.sayHello(name);
    }

}