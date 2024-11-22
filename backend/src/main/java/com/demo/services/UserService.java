package com.demo.services;

import org.springframework.data.domain.Page;

import com.demo.dto.UserData;
import com.demo.model.User;

public interface UserService {
    
    public User createNewUser(UserData userdata);
    Page<User> getUsers(String query, int page, int size);
}
