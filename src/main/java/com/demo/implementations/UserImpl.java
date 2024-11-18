package com.demo.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.User;
import com.demo.repositories.UserRepository;
import com.demo.services.UserService;
import com.demo.services.PasswordService;

public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordService passService;

    @Override
    public User createNewUser(String name, String edv, String email, String pass) {

        var users = userRepository.searchUser(edv);

        if (!users.isEmpty()) {
            return null;
        }
        
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
        var users = userRepository.searchUser(query);

        return users;
    }
    
}
