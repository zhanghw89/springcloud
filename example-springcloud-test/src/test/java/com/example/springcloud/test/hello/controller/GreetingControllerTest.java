package com.example.springcloud.test.hello.controller;

import com.example.springcloud.test.hello.model.Person;
import com.example.springcloud.test.hello.service.GreetingService;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import jdk.nashorn.internal.parser.JSONParser;
import net.minidev.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.http.MediaType;
import org.springframework.test.util.JsonExpectationsHelper;
import org.springframework.test.util.JsonPathExpectationsHelper;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * description : 单元测试
 *
 * @author : 张会文
 * @date : Created in 下午4:56 2018/4/3
 */
public class GreetingControllerTest {

    @InjectMocks
    private GreetingController greetingController;

    @Mock
    private GreetingService greetingService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(greetingController).build();
    }


    @Test
    public void greeting() throws Exception {
        String resuslt = "hello,shiwen";
        when(greetingService.sayHello(anyString())).thenReturn(resuslt);

        //要加/
        String path = "/greeting";
        MvcResult zhanghuiwen = mockMvc.perform(get(path)
                .param("name", "zhanghuiwen")
        )
                .andDo(print())
                .andExpect(content().string(resuslt))
                .andExpect(status().isOk())
                .andReturn();
//        verify(greetingService.sayHello(""));
    }

    @Test
    public void ageIncrease() throws Exception {
        String path = "/ageIncrease";
        Person p = new Person();
        p.setAge(10);
        p.setName("shiwen");
        p.setAddress("BeijingHaidian");
        when(greetingService.addAge(any(Person.class))).thenReturn(p);

        //将对象转json
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(p);

        mockMvc.perform(get(path)
                        .contentType(MediaType.APPLICATION_JSON_UTF8) //不能缺少
                .accept(MediaType.APPLICATION_JSON_UTF8)
//                .param("user", result)   //这样是错误的
//                .requestAttr("user",result) //也是错误的
                        .content(result) //需要参数加@RequestBody
        )
                .andDo(print())
                .andExpect(content().string(result))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void testPost() throws Exception {
        String path = "/testPost";
        Person p = new Person();
        p.setAge(10);
        p.setName("shiwen");
        p.setAddress("Beijing.Haidian");

        Person p2 = new Person();
        p2.setAge(11);
        p2.setName("shiwen");
        p2.setAddress("Beijing.Haidian");

        when(greetingService.addAge(any(Person.class))).thenReturn(p);

        //将对象转json
        ObjectMapper mapper = new ObjectMapper();
        String result = mapper.writeValueAsString(p);

        ObjectMapper mapper2 = new ObjectMapper();
        String result2 = mapper.writeValueAsString(p2);


        //请求方式1：.param("user", result))
        mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
//                .param("user", result)
//                .param("user1", result)

                .content(result)
                .content(result2) //TODO 这里会覆盖掉result的值，不知道怎么解决
        )
                .andDo(print())
                .andExpect(content().string(result))
                .andExpect(status().isOk())
                .andReturn();

        //.param("user", result))
       /* mockMvc.perform(post(path, p)
                .header("Content-Type", "application/json;charset=UTF-8")
        )
                .andDo(print())
                .andExpect(content().string(result))
                .andExpect(status().isOk())
                .andReturn();*/
    }
}
