package com.example.springcloud.provider.example.dao;

import com.example.springcloud.provider.example.AbstractTest;
import com.example.springcloud.provider.example.entity.User;
import org.junit.Assert;
import org.junit.Test;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by shiwen on 2017/9/10.
 */
public class UserMapperTest extends AbstractTest{

    @Resource
    private UserMapper userMapper;

    @Test
    public void findByState() throws Exception {
//        userMapper.findByState("")
    }

    @Test
    public void findById() throws Exception {
        User user = userMapper.findById("1");
        Assert.assertNotNull(user);
        Assert.assertEquals("zhangsan",user.getName());
    }

}