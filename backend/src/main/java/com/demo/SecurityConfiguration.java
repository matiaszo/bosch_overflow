package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.demo.dto.Token;
import com.demo.filters.JWTAuthenticationFilter;
import com.demo.services.JWTService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    JWTService<Token> jwtService;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                // .requestMatchers("/user").permitAll()
                .requestMatchers("/**").permitAll()
                // .requestMatchers("/**").authenticated()
                .anyRequest().anonymous()
            )
            .addFilterBefore(new JWTAuthenticationFilter(jwtService), UsernamePasswordAuthenticationFilter.class)
            .build();
    }

}