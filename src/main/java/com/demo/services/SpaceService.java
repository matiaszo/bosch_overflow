package com.demo.services;

import java.util.List;

import com.demo.dto.Token;
import com.demo.model.Space;

public interface SpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(Token token, String title, String description);
    public boolean deleteSpace(long idSpace);
    List<Space> getSpaces(String query, int page, int size);
}
