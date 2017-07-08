package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class CategoryAlreadyExistsException extends AlreadyExistsException {

    public CategoryAlreadyExistsException(AbstractEntity entity) {
        super(entity);
    }
}
