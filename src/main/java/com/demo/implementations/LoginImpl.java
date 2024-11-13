package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Token;
import com.demo.repositories.UserRepository;
import com.demo.services.JWTService;
import com.demo.services.LoginService;
import com.demo.services.PasswordService;

public class LoginImpl implements LoginService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordService passwordService;

    @Autowired
    JWTService<Token> jwtService;
    
    @Override
    public String login(String edv, String password) {

        if (edv == null || password == null) {
            return "All fields must be filled in!";
        }

        var users = userRepository.findByEdv(edv);

        if (users.isEmpty()) {
            return "User not found!";
        }

        var user = users.get(0);

        if (!passwordService.checkPassword(password, user.getPass())) {
            return "Invalid password!";
        }

        Token token = new Token();
        token.setId(user.getId());
        token.setPermissions(userRepository.searchPermissions(user.getId()));

        var jwt = jwtService.get(token);
        
        return jwt;
    }
    
}
