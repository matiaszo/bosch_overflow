package com.demo.implementations;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demo.services.PasswordService;

public class PasswordImpl implements PasswordService{

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public String encryptPassword(String password) {

        return encoder.encode(password);
    }

    @Override
    public boolean checkPassword(String rawPassword, String encodedPassword) {

        return encoder.matches(rawPassword, encodedPassword);
    }
   
}
