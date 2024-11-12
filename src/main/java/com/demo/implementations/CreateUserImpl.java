package com.demo.implementations;

import com.demo.model.User;
import com.demo.services.CreateUserService;

public class CreateUserImpl implements CreateUserService {

    @Override
    public boolean isEdvValid(String edv) {
        
        // IMPLEMENTAR

        return false;
    }

    @Override
    public boolean isUsernameValid(String username) {
        
        // IMPLEMENTAR

        return false; 
    }

    @Override
    public boolean isEmailValid(String email) {
        
        // IMPLEMENTAR

        return false;
    }

    @Override
    public boolean isPasswordValid(String password) {
        
        // IMPLEMENTAR

        return false;
    }

    @Override
    public User createNewUser(String name, String edv, String email, String pass) {
        
        // IMPLEMENTAR

        return null;
    }
    
}
