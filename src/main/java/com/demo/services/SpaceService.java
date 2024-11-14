package com.demo.services;

import com.demo.dto.Token;
import com.demo.model.Space;

public interface SpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(Token token, String title, String description);
    public boolean deleteSpace(Long idSpace);
}
