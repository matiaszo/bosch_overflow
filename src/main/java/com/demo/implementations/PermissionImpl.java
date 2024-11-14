package com.demo.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Permission;
import com.demo.repositories.UserRepository;
import com.demo.services.PermissionService;

public class PermissionImpl implements PermissionService {

    @Autowired
    UserRepository userRepository;

    @Override
    public Permission createNewPermission(Long idUser, Long idSpace) {

        // IMPLEMENTAR

        return null;
    }

    @Override
    public boolean validatePermission(Long idUser, Long idSpace) {

        List<Permission> permissionUsers = userRepository.searchPermissionsSpaces(idUser, idSpace);

        
        if (permissionUsers.isEmpty()) {
            return false;
        }
        return false;
    }
    
}
