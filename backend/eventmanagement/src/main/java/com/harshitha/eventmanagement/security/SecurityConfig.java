package com.harshitha.eventmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration

public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

            // Disable CSRF
            .csrf(csrf -> csrf.disable())

            // Disable Form Login
            .formLogin(form -> form.disable())

            // Disable HTTP Basic
            .httpBasic(httpBasic -> httpBasic.disable())

            // Stateless Session
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // Authorization Rules
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers(
                            "/api/auth/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/v3/api-docs/**"
                    ).permitAll()

                    .anyRequest().authenticated()
            )
            

            // JWT Filter
            .addFilterBefore(
                    jwtAuthenticationFilter,
                    UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }

}