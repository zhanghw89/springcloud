package com.example.springcloud.provider.example.service.impl;

import com.example.springcloud.provider.example.dao.UserMapper;
import com.example.springcloud.provider.example.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by shiwen on 2017/9/10.
 * 特点：快速
 */

public class UserServiceImplTest {
    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;


    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);

        User user = new User();
        user.setId("1");
        user.setName("zhangsan");
//        when(userMapper.findById("1")).thenReturn(user);
        when(userMapper.findById(any(String.class))).thenReturn(user);
    }


    @Test
    public void getUserName() throws Exception {
        String userName = userService.getUserName("2");
        Assert.assertEquals("zhangsan", userName);


    }

}