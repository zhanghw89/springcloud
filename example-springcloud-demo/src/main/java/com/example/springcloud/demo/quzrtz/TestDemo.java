package com.example.springcloud.demo.quzrtz;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * description :
 *
 * @author : 张会文
 * @date : Created in 上午11:20 2018/3/30
 */
public class TestDemo {
    @Test
    public void test() throws InterruptedException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("quartz-context.xml");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
