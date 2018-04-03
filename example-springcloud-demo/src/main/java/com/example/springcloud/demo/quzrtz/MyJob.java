package com.example.springcloud.demo.quzrtz;

import org.springframework.stereotype.Component;

/**
 * Created by L on 2017-07-18.
 */
@Component
public class MyJob {

    public void execute() {
        System.out.println("Hello" +
                "这里是我们的任务代码！！！！");
    }
}