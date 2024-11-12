package com.demo.services;

import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    public String encryptPassword(String password);
    public boolean checkPassword(String rawPassword, String encodedPassword);
}
