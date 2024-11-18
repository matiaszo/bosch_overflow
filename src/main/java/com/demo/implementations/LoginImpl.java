package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Token;
import com.demo.repositories.UserRepository;
import com.demo.services.JWTService;
import com.demo.services.LoginService;
import com.demo.services.PasswordService;

public class LoginImpl implements LoginService{

    @Override
    public String login(String edv, String password) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    
    
}
