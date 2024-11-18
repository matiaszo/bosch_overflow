package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.LoginData;
import com.demo.dto.Token;
import com.demo.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping
    public String login(@RequestBody LoginData data) {

        // if (token == null) {
        //     return "Invalido";
        // }

        var users = userRepository.searchEdv(data.edv());

        if (users.isEmpty()) {
            return "The user do not exists.";
        }

        var indexUser = users.get(0);

        if (!encoder.matches(data.pass(), indexUser.getPass())) {
            return "The password is incorrect.";
        }

        return "ok!";

    }

}
