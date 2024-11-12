package com.demo.services;

import com.demo.model.Space;

public interface CreateSpaceService {
    public boolean isSpaceTitleValid(String title);
    public Space createNewSpace(String title, String description);
}
