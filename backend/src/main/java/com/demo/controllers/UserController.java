package com.demo.controllers;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.demo.dto.UserData;
import com.demo.model.User;
import com.demo.repositories.UserRepository;
import com.demo.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public User createUser(@RequestBody UserData data) {

        boolean senha = MyPasswordValidator.Validate(data.pass());

        if (senha) {
            
            System.out.println("senha valida");

            User user = userService.createNewUser(data);
            System.out.println("criou");
    
            if (user == null) {
                return null;
            }

            System.out.println("nao e nulo");

    
            userRepository.save(user);

            System.out.println("salvou");

    
            return user;
        }

        return null;

    }

    @GetMapping
    public ArrayList<User> FindUser(@RequestParam String query,@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer size) {

        return userService.getUsers(query, page, size);

    }

}
