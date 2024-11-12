package com.demo.implementations;

import com.demo.model.Permission;
import com.demo.services.PermissionService;

public class PermissionImpl implements PermissionService {

    @Override
    public Permission createNewPermission(Long idUser, Long idSpace) {

        // IMPLEMENTAR

        return null;
    }

    @Override
    public boolean validatePermission(Long idUser, Long idSpace) {

        // IMPLEMENTAR

        return false;
    }
    
}
