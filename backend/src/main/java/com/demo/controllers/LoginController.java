package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginData;
import com.demo.services.LoginService;

@RestController
@RequestMapping("/auth")
public class LoginController {


    @Autowired
    LoginService loginService;

    @PostMapping
    public String login(@RequestBody LoginData data) {
       return loginService.login(data);
    }
}
