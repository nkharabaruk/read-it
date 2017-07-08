package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class ProfileAlreadyExistsException extends AlreadyExistsException {

    public ProfileAlreadyExistsException(AbstractEntity entity) {
        super(entity);
    }
}
