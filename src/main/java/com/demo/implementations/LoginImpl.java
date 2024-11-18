package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.demo.dto.Token;
import com.demo.repositories.UserRepository;
import com.demo.services.JWTService;
import com.demo.services.LoginService;
import com.demo.services.PasswordService;

public class LoginImpl implements LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JWTService<Token> jwtService;

    @Override
    public String login(String edv, String password) {

        var users = userRepository.searchEdv(edv);

        if (users.isEmpty()) {
            return "The user do not exists.";
        }

        var user = users.get(0);

        if (!encoder.matches(password, user.getPass())) {
            return "The password is incorrect.";
        }

        Token token = new Token();
        token.setId(user.getId());
        var jwt = jwtService.get(token);

        return jwt;

    }

}
