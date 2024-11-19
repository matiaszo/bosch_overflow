package com.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.SpaceData;
import com.demo.dto.Token;
import com.demo.implementations.JWTImpl;
import com.demo.implementations.PermissionImpl;
import com.demo.model.Space;
import com.demo.repositories.SpaceRepository;
import com.demo.services.SpaceService;

@RestController
@RequestMapping("/spaces")
public class SpaceController { 


    @Autowired
    SpaceService spaceService;

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    PermissionImpl permissionImpl;

    @Autowired
    JWTImpl jwtImpl;


    @GetMapping
    public Page<Space> GetSpace(@RequestParam String query, @RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        return spaceService.getSpaces(query, page, size);
    }

    @PostMapping
    public String postMethodName(@RequestBody SpaceData data) {

        Token token = jwtImpl.validate(data.token());

        if (token == null) {
            return "Invalid token!";
        }

        Space space = spaceService.createNewSpace(token, data.title(), data.description());

        spaceRepository.save(space);

        return "Space create!";
        
    }

    @DeleteMapping("/{id}")
    public String deleteSpace(@PathVariable Long id, @RequestParam String token) {

        Token myToken = jwtImpl.validate(token);
        
        if (myToken == null) {
            return "invalid token";
        }

        long userId = myToken.getId();

        int level = permissionImpl.validatePermission(userId, id); 
        
        boolean trash = spaceService.deleteSpace(id);  

        if (level < 2) {
            return "You dont have permission to do that";
        }

        if (trash) {
            return "delete ok";
        }

        return "Space not found!";
    }
    
    
}
