package com.example.springcloud.test.hello;

import com.example.springcloud.test.hello.controller.GreetingController;
import com.example.springcloud.test.hello.service.GreetingService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
                .param("name", "zhanghuiwen"))
                .andDo(print())
                .andExpect(content().string(resuslt))
                .andExpect(status().isOk())
                .andReturn();

//        verify(greetingService.sayHello(""));


    }
}
