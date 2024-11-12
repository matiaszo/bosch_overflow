package com.demo.services;

import com.demo.model.User;

public interface CreateUserService {
    public User createNewUser(String name, String edv, String email, String pass);
}
