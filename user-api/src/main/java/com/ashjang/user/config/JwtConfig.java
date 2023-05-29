package com.ashjang.user.config;

import com.ashjang.user.domain.token.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {
    @Bean
    public JwtProvider jwtAuthenticationProvider() {
        return new JwtProvider();
    }
}