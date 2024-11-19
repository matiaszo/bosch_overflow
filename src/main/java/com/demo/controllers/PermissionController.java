package com.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.dto.PermissionData;
import com.demo.model.Permission;
import com.demo.services.PermissionService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    PermissionService permissionService;
    
    @PostMapping
    public Permission postMethodName(@RequestBody PermissionData data) {
        
        Permission permission = permissionService.createNewPermission(data.userId(), data.spaceId(), data.adm());
        
        return permission;
    }
    
}
