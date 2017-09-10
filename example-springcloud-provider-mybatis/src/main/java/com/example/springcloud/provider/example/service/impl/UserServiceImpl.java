package com.example.springcloud.provider.example.service.impl;

import com.example.springcloud.provider.example.dao.UserMapper;
import com.example.springcloud.provider.example.entity.User;
import com.example.springcloud.provider.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by shiwen on 2017/9/10.
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public String getUserName(String id) {
        User user = userMapper.findById(id);
        return user.getName();
    }
}
