package com.example.springcloud.test.hello.controller;

import com.example.springcloud.test.hello.model.Person;
import com.example.springcloud.test.hello.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * description : protobuf测试类
 *
 * @author : 张会文
 * @date : Created in 上午9:34 2018/4/4
 */
@RestController
public class ProtobufController {

    @Autowired
    private GreetingService greetingService;

    @GetMapping("hello/protobuf")
    @ResponseBody
    ResponseEntity<Person> protoBuf(RequestEntity<Person> requestEntity) {
        Person person = requestEntity.getBody();
        Assert.notNull(person);
        Assert.notNull(person.getName());
        Person person1 = greetingService.addAge(person);
        return ResponseEntity.ok(person1);
    }


}
