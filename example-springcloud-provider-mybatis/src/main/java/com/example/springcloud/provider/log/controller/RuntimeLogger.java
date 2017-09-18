package com.example.springcloud.provider.log.controller;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shiwen on 2017/9/12.
 * 运行时logger，日志状态修改
 */
@RestController
@RequestMapping("/runtime/logger/config")
public class RuntimeLogger {

    @PostMapping("/level")
    public void changeLevel(String loggerName,String logLevel) {
        LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
        context.getLogger(loggerName).setLevel(Level.valueOf(logLevel));
    }

}
