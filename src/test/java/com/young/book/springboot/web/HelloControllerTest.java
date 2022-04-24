package com.young.book.springboot.web;

import com.young.book.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath; <-- jsonpath의 is가 안되는 이유는 잘못 import를 하고있었기 때문.
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        } //WebMvcTest는 service를 스캔하지 않기 때문에 스캔 대상에서 SecurityConfig를 제거함
)
public class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    @WithMockUser(roles="USER")
    public void hello_return() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello"))
                .andExpect(status().isOk()) // HTTP Header의 Status 검증
                .andExpect(content().string(hello)); // 응답 본문의 내용을 검증
    }

    @Test
    @WithMockUser(roles="USER")
    public void helloDto_return() throws Exception {
        String name = "hello";
        int amount = 1;

        mvc.perform(
                get("/hello/dto")
                    .param("name", name)
                    .param("amount", String.
                            valueOf(amount))) // 숫자,날짜는 문자열로 변경해야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))  // JSON 응답값을 필드별로 검증 , $ 기준으로 필드명 명시
                .andExpect(jsonPath("$.amount", is(amount)));

    }
}
