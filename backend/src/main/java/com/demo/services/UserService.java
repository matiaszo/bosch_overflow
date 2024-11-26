package com.demo.services;

import java.util.ArrayList;

import com.demo.dto.UserData;
import com.demo.model.User;

public interface UserService {
    
    public User createNewUser(UserData userdata);
    ArrayList<User> getUsers(String query, int page, int size);
}
