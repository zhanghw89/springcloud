package com.example.springcloud.test.hello.controller;

import com.example.springcloud.test.hello.model.Person;
import com.example.springcloud.test.hello.service.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;



/**
 * description :
 *
 * @author : 张会文
 * @date : Created in 上午9:39 2018/4/4
 */
public class ProtobufControllerTest {
    @InjectMocks
    private ProtobufController protobufController;

    @Mock
    private GreetingService greetingService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(protobufController).build();
    }


    @Test
    public void protoBuf() {
        String path = "/ageIncrease";
        Person p = new Person();
        p.setAge(10);
        p.setName("shiwen");
        p.setAddress("BeijingHaidian");
        when(greetingService.addAge(any(Person.class))).thenReturn(p);

        //将对象转json
    }
}
