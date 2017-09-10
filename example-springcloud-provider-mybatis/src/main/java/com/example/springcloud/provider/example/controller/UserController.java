package com.example.springcloud.provider.example.controller;

import com.example.springcloud.provider.example.dao.UserMapper;
import com.example.springcloud.provider.example.entity.User;
import com.example.springcloud.provider.example.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by shiwen on 2017/9/10.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/name")
    public String getUserName(String id) {
        String userName = null;
        userName = userService.getUserName("1");

        return "get UserName success!userName = " + userName;
    }
}
