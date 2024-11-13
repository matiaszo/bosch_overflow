package com.demo.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.User;
import com.demo.services.UserService;
import com.demo.services.PasswordService;

public class CreateUserImpl implements UserService {
    
    @Autowired
    PasswordService passService;

    @Override
    public User createNewUser(String name, String edv, String email, String pass) {
        
        User user = new User();

        String cripto = passService.encryptPassword(pass);

        user.setName(name);
        user.setEdv(edv);
        user.setEmail(email);
        user.setPass(cripto);

        return user;
    }

    @Override
    public List<User> getUsers(String query, int page, int size) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsers'");
    }
    
}
