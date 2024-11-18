package com.demo.services;


import org.springframework.data.domain.Page;

import com.demo.dto.Token;
import com.demo.model.Space;

public interface SpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(Token token, String title, String description);
    public boolean deleteSpace(long idSpace);
    Page<Space> getSpaces(String query, int page, int size);
}
