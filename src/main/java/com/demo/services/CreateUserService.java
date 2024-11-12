package com.demo.services;

import com.demo.model.User;

public interface CreateUserService {
    public boolean isEdvValid(String edv);
    public boolean isUsernameValid(String username);
    public boolean isEmailValid(String email);    
    public boolean isPasswordValid(String password);
    public User createNewUser(String name, String edv, String email, String pass);
}
