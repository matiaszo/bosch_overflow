package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.dto.Token;
import com.demo.model.Space;
import com.demo.repositories.SpaceRepository;
import com.demo.services.PermissionService;
import com.demo.services.SpaceService;

public class SpaceImpl implements SpaceService {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    PermissionService permissionService;

    @Override
    public boolean isSpaceTitleValid(String title) {

        var spaces = spaceRepository.findByTitle(title);
    
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
        
        permissionService.createNewPermission(token.getId(), space.getId());
        spaceRepository.saveAndFlush(space);
        
        return space;

    }

    @Override
    public boolean deleteSpace(long idSpace) {
        
       Space spaces = spaceRepository.findById(idSpace);

        if (spaces == null) {
            return false;
        }

        spaceRepository.delete(spaces);

        return true;
    }  

    
}
