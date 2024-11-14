package com.demo.services;

import java.util.List;

import com.demo.model.User;

public interface UserService {
    
    public User createNewUser(String name, String edv, String email, String pass);
    List<User> getUsers(String query, int page, int size);
}
