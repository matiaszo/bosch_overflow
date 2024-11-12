package com.demo.services;

import com.demo.model.Space;

public interface SpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(String title, String description);
    public boolean deleteSpace(Long idSpace);
}
