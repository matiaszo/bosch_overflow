package com.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
    public String createUser(@RequestBody UserData data) {

        User user = userService.createNewUser(data.name(), data.edv(), data.email(), data.pass());

        userRepository.save(user);

        return "User create!";
    }

    @GetMapping("?{query}&{page}&{size}")
    public List<User> FindUser(@PathVariable String query, Integer page, Integer size) {

        var users = userService.getUsers(query, 1, 5);

<<<<<<< HEAD
        return "User create!";
=======
        return users;
>>>>>>> c3a7e313416dced066b9480a1c542344cc235d31
    }

}
