package com.demo.implementations;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import com.demo.dto.SpaceGet;
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

        permissionService.createNewPermission(token.getId(), space.getId(), true);
        
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

    // ! CÓDIGO DA EQUIPE DE INTEGRAÇÃO 
    @Override
    public ArrayList<SpaceGet> getSpaces(String name, Integer page, Integer limit) {
        System.out.println(name +  page + limit);
        // findByNameContains: procura no banco por NOMES que CONTENHAM (%LIKE%) o parâmetro name
        var results = spaceRepository.findByTitleContains(name, PageRequest.of(page, limit)); 

        ArrayList<SpaceGet> list = new ArrayList<>();

        for (int i = 0; i < results.size(); i++) {
            list.add(new SpaceGet(results.get(i).getId(), results.get(i).getTitle(), results.get(i).getDescription()));
        }


        // var resultado = spaceRepository.findAll(PageRequest.of(page, limit));
        System.out.println(list);
        
        return list;
    }

    
    // CÓDIGO DA OUTRA EQUIPE MAS NÃO TAVA FUNCIONANDO :(

    // 2024-11-25T09:31:29.150-03:00  WARN 9852 --- [demo] [nio-8080-exec-2] ration$PageModule$WarningLoggingModifier : Serializing PageImpl instances as-is is not supported, meaning that there is no guarantee about the stability of the resulting JSON structure!
    // For a stable JSON structure, please use Spring Data's PagedModel (globally via @EnableSpringDataWebSupport(pageSerializationMode = VIA_DTO))
    // or Spring HATEOAS and Spring Data's PagedResourcesAssembler as documented in https://docs.spring.io/spring-data/commons/reference/repositories/core-extensions.html#core.web.pageables.
    //Page 1 of 0 containing UNKNOWN instances

    // @Override
    // public Page<Space> getSpaces(String query, int page, int size) {
        // Pageable pageable = PageRequest.of(page, size);                              
        // var spaces = spaceRepository.searchSpace(query, pageable);
        // System.out.println(spaces);
        // return spaces;
    // }  

    
}
