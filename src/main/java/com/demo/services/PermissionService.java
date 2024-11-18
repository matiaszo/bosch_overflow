package com.demo.services;

import com.demo.model.Permission;

public interface PermissionService {
    
    public Permission createNewPermission(long idUser, long idSpace);
    public int validatePermission(Long idUser, Long idSpace);
}
