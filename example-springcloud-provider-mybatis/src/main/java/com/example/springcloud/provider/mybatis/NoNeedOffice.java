package com.example.springcloud.provider.mybatis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by shiwen on 2017/9/10.
 * 定义一个注解，定义在Dao层，sql中不需要注入OfficeID
 */
@Target({ElementType.METHOD, ElementType.TYPE})  //注解可以修饰类和方法
@Retention(RetentionPolicy.RUNTIME)  //运行时有效
public @interface NoNeedOffice {

}
