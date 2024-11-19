package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.demo.model.User;
import com.demo.repositories.UserRepository;
import com.demo.services.UserService;
import com.demo.services.PasswordService;

@EnableSpringDataWebSupport
public class UserImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordService passService;

    @Override
    public User createNewUser(String name, String edv, String email, String pass) {

        var users = userRepository.searchEdv(edv);

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
    public Page<User> getUsers(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.searchUser(query, pageable);
    }
    
}