package com.demo.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.demo.dto.Token;
import com.demo.model.Space;
import com.demo.repositories.SpaceRepository;
import com.demo.services.PermissionService;
import com.demo.services.SpaceService;

@EnableSpringDataWebSupport
public class SpaceImpl implements SpaceService {

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    PermissionService permissionService;

    @Override
    public boolean isSpaceTitleValid(String title) {

        var spaces = spaceRepository.findByTitle(title);
    
        return spaces.isEmpty();
    }

    @Override
    public Space createNewSpace(Token token, String title, String description) {

        if (!isSpaceTitleValid(title)) {
            return null; 
        }

        Space space = new Space();
        
        space.setTitle(title);
        space.setDescription(description);
        
        spaceRepository.save(space);

        permissionService.createNewPermission(token.getId(), space.getId());
        
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

    @Override
    public Page<Space> getSpaces(String query, int page, int size) {
        
        Pageable pageable = PageRequest.of(page, size);
        return spaceRepository.searchSpace(query, pageable);
    }  

    
}
