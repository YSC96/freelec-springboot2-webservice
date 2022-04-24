package com.young.book.springboot.config.auth;

import com.young.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable() //h2-console 사용하기 위해 비활성화
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //전체 열람 권한
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //USER권한을 가진 사람만 가능하도록 설정
                .anyRequest().authenticated() // 그외 URL은 로그인한 사용자들만 열람 가능
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint() // OAuth2 로그인 성공 후 사용자 정보를 가져올때 설정
                .userService(customOAuth2UserService); // 로그인 성공시 추가적으로 진행하고자 하는 기능 명시

        /* csrf 비활성화 하는 이유 ?
        CSRF란, 사용자가 의도하지 않은 요청을 통한 공격을 의미하는데 그것을 보호해주는 함수.
        보통 템플릿 엔진을 사용하여 서버 측에서 전체 HTML을 생성하는 구조일때(=사이트 간 요청이 발생하기 쉬운 웹)일때 취약하므로 사용했으나
        최신 애플리케이션은 주로 JSON 방식으로 통신하는 REST API로 설계가 되어있음.
        REST API는 HTTP 형식을 따르기 때문에 서버쪽의 세션이나 브라우저 쿠키에 의존하지 않음.
        그리하여 REST API는 CSRF 공격을 받을 가능성이 존재하지 않으므로 CSRF를 비활성화 하고 있음.
         */
    }
}
