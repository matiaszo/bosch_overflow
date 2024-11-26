package com.demo.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Permission;
import com.demo.model.Space;
import com.demo.model.User;
import com.demo.repositories.PermissionRepository;
import com.demo.repositories.SpaceRepository;
import com.demo.repositories.UserRepository;
import com.demo.services.PermissionService;

public class PermissionImpl implements PermissionService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpaceRepository spaceRepository;

    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Permission createNewPermission(long idUser, long idSpace, boolean adm) {

        User user = userRepository.findById(idUser);
        Space space = spaceRepository.findById(idSpace);

        Permission permission = new Permission();

        permission.setUser(user);
        permission.setSpace(space);
        permission.setAdm(adm);

        permissionRepository.saveAndFlush(permission);

        return permission;
    }

    @Override
    public int validatePermission(Long idUser, Long idSpace) {

        List<Permission> permissionUsers = userRepository.searchPermissionsSpaces(idUser, idSpace);

        System.out.println("A lista de usuários encontrada para validação de permissão: " + permissionUsers);

        if (permissionUsers.isEmpty()) {
            System.out.println("Vish a lista estava vazia");
            return 0;
        }
        if (permissionUsers.get(0).getAdm().equals(0)) {
            System.out.println("Vish, a permissão do usuário era essa aqui ó: " + permissionUsers.get(0).getAdm());
            return 1;
        }
        return 2;
    }
    
}
