package com.young.book.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@EnableJpaAuditing // JPA Auditing 활성화
//테스트환경에서 스캔할때 EnableJpaAuditing를 사용하려면 @Entity 클래스가 최소 하나 이상 필요한데 @WebMvcTest에는 없기때문에 주석처리
@SpringBootApplication
public class Application {
    public static void main(String[] args){

        SpringApplication.run(Application.class, args);
    }
}
