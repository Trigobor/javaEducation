package com.springapp.first.javamodule11.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration  // Говорит Spring, что этот класс является конфигурационным
@EnableWebSecurity  // Включает поддержку Spring Security
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // Требует авторизацию
                .httpBasic(withDefaults()) // Включает Basic Auth
                .csrf(csrf -> csrf.disable()); // Отключает CSRF

        return http.build();
    }

}