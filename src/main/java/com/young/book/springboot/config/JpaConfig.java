package com.young.book.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration // 테스트환경 @WebMvcTest는 @Configuration은 스캔하지 않음.
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {}
