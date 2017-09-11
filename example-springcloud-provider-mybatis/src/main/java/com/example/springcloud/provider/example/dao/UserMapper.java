package com.example.springcloud.provider.example.dao;

import com.example.springcloud.provider.example.entity.User;
import com.example.springcloud.provider.mybatis.NoNeedOffice;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by shiwen on 2017/9/6.
 * mybatis 不允许有重复的id
 */
public interface UserMapper {

    @Select("select * from user where name = #{name}")
    User findByState(@Param("name") String name);

//    @NoNeedOffice
    @Select("select * from `user` where `id` = #{id}")
    User findById(@Param("id") String id);

    @Select("select * from `user` where `id` = #{id} and 'name=' #{name} ")
    User findByIdAndName(@Param("id") String id, @Param("name") String name);

}
