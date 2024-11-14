package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Token;
import com.demo.model.Space;
import com.demo.repositories.SpaceRepository;
import com.demo.services.SpaceService;

public class SpaceImpl implements SpaceService {

    @Autowired
    SpaceRepository spaceRepository;

    @Override
    public boolean isSpaceTitleValid(String title) {

        var spaces = spaceRepository.findByTitulo(title);
    
        if (spaces.isEmpty()) {
            return false;
        }

        return true;
    }

    @Override
    public Space createNewSpace(Token token, String title, String description) {

        if (!isSpaceTitleValid(title)) {
            return null;
        }

        Space space = new Space();

        space.setTitle(title);
        space.setDescription(description);

        return space;

    }

    @Override
    public boolean deleteSpace(Long idSpace) {
        
        // if (idSpace.isEmpty()){
            
        // }

        return false;
    }  
}
