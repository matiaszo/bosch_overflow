package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.implementations.*;
import com.demo.services.*;

import com.demo.dto.Token;

@Configuration
public class DependencyConfiguration {

    @Bean
    public AnswerService answerService(){
        return new AnswerImpl();
    }

    @Bean
    public UserService createUserService(){
        return new UserImpl();
    }

    @Bean
    public JWTService<Token> JWTService(){
        return new JWTImpl();
    }

    @Bean
    public LoginService loginService(){
        return new LoginImpl();
    }

    @Bean
    public PasswordService passwordService(){
        return new PasswordImpl();
    }

    @Bean
    public QuestionService questionService(){
        return new QuestionImpl();
    }

    @Bean
    public SpaceService spaceService(){
        return new SpaceImpl();
    }

    @Bean
    public PermissionService permissionService(){
        return new PermissionImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }
}
