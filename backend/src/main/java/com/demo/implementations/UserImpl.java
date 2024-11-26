package com.demo.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.demo.dto.UserData;
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
    public User createNewUser(UserData data) {  // ! NÃO HAVIA SALVAMENTO PELO REPOSITORY DO OBJETO DE USER CRIADO
                                                // ! E O PARÂMETRO NÃO ERA UM DTO MAS SIM QUATRO STRINGS 

        var users = userRepository.searchEdv(data.edv());

        if (!users.isEmpty()) {
            return null;
        }
        
        User user = new User();

        String cripto = passService.encryptPassword(data.pass());

        user.setName(data.name());
        user.setEdv(data.edv());
        user.setEmail(data.email());
        user.setPass(cripto);

        userRepository.save(user);

        return user;
    }

    @Override
    public ArrayList<User> getUsers(String query, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ArrayList<>(userRepository.findAll(pageable).toList());
    }
    
}
