package com.demo.services;

import com.demo.dto.Token;

public interface LoginService {
    String login(String edv, String password);
}