package com.demo.services;

import com.demo.model.Permission;

public interface PermissionService {
    
    public Permission createNewPermission(Long idUser, Long idSpace);
    public boolean validatePermission(Long idUser, Long idSpace);
}
