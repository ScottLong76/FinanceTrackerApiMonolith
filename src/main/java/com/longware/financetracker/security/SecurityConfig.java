package com.longware.financetracker.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        System.out.println("SecurityConfig.securityFilterChain()");
        
        return http.csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(a -> a
        .antMatchers("/v3/**", "/swagger-ui/**", "/api-docs/**", "/v3/api-docs/**").permitAll()
        .anyRequest().authenticated()).build();

    }
}
