package com.example.springcloud.provider.demo;

public class Demo {
    public volatile boolean isOk = true;

    public void test() {
        synchronized (this) {
            isOk = false;
        }
    }
}
