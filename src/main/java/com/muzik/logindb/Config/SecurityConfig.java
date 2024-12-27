package com.muzik.logindb.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

// 사용자 보안 설정
@Configuration
@EnableWebSecurity              // 웹보안 활성화(2.7버전에서 extends와 동일)
@RequiredArgsConstructor        // 클래스 자동생성(외부 클래스 추가)
public class SecurityConfig {

    // 1. 비밀번호 암호화 설정
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 2. 필터링(try~catch 대신 throw 선언)
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 2-1. 매핑 권한 (permitALL(), hasRole(), hasAnyRole())
        http.authorizeHttpRequests((auth) -> {

            // Controller의 모든 매핑에 대한 권한을 부여(** => 모든 매핑 대응)
            auth.requestMatchers("/", "/index").permitAll();            // 시작페이지(메인페이지)는 모든 사용자 접근 가능
            auth.requestMatchers("/result").permitAll();                  // result 매핑은 모든 사용자 접근 가능
            auth.requestMatchers("/user").hasRole("USER");                // USER 사용자만 /user 매핑으로 접근 가능
            auth.requestMatchers("/admin").hasRole("ADMIN");              // ADMIN 사용자만 /admin 매핑으로 접근 가능

        });

        // 2-2. 로그인
        http.formLogin(login -> login
                .defaultSuccessUrl("/result", true)   // 로그인 성공 시, /result로 이동
                .permitAll()                                                 // 모든 사용자 접근 가능
        );

        // 2-3. csrf보안(일단 비활성화, http의 변조방지)
        http.csrf(AbstractHttpConfigurer::disable);

        // 2-4. 로그아웃

        return http.build();
    }

}