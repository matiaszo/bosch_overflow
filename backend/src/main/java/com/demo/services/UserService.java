package com.demo.services;

import org.springframework.data.domain.Page;

import com.demo.model.User;

public interface UserService {
    
    public User createNewUser(String name, String edv, String email, String pass);
    Page<User> getUsers(String query, int page, int size);
}
