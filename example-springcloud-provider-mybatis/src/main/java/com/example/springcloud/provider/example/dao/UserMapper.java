package com.example.springcloud.provider.example.dao;

import com.example.springcloud.provider.example.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by shiwen on 2017/9/6.
 */
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findByState(@Param("name") String name);

    @Select("select * from `user` where `id` = #{id}")
    User findById(@Param("id") String id);

}
