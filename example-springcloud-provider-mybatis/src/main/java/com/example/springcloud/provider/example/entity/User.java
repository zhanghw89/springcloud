package com.example.springcloud.provider.example.entity;

import com.example.springcloud.provider.mybatis.Office;

/**
 * Created by shiwen on 2017/9/6.
 */
public class User extends Office {
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
