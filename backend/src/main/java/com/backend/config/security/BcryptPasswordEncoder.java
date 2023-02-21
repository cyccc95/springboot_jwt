package com.backend.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
// 패스워드를 검증하는 메서드가 담긴 클래스
public class BcryptPasswordEncoder {

    @Bean
    public PasswordEncoder getPasswordEndocer() {
        return new BCryptPasswordEncoder();
    }
}
