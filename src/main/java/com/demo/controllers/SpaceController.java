package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.SpaceData;
import com.demo.model.Space;
import com.demo.model.User;
import com.demo.repositories.SpaceRepository;
import com.demo.services.SpaceService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/spaces")
public class SpaceController { 

    @Autowired
    SpaceService spaceService;

    @Autowired
    SpaceRepository spaceRepository;

    @GetMapping("?{query}&{page}&{size}")
    public Page<Space> GetSpace(@PathVariable String query,@RequestParam(defaultValue = "0") Integer page,@RequestParam(defaultValue = "10") Integer size) {
        return spaceService.getSpaces(query, page, size);
    }

    @PostMapping
    public String postMethodName(@RequestBody SpaceData data) {

        Space space = spaceService.createNewSpace(data.token(), data.title(), data.description());

        spaceRepository.save(space);

        return "Space create!";
        
    }
    
    
}
