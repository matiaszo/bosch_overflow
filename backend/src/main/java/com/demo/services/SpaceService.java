package com.demo.services;


import java.util.ArrayList;

import com.demo.dto.SpaceGet;
import com.demo.dto.Token;
import com.demo.model.Space;

public interface SpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(Token token, String title, String description);
    public boolean deleteSpace(long idSpace);
    // Page<Space> getSpaces(String query, int page, int size);                             // CÓDIGO DA EQUIPE DO BACK

    // ! CÓDIGO DA EQUIPE DA INTEGRAÇÃO
    public ArrayList<SpaceGet> getSpaces(String name, Integer page, Integer limit);            
}
