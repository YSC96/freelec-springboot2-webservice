package com.young.book.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT) // 랜덤 포트 실행
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void main_loading(){
        //when
        String body = this.restTemplate.getForObject("/",String.class); // 주어진 URL 주소로 HTTP GET 메서드로 객체로 결과를 반환받는다

        //then
        assertThat(body).contains("스프링부트 웹서비스");
    }

}
