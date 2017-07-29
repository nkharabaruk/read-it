package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class AlreadyExistsException extends RuntimeException {

    AlreadyExistsException(AbstractEntity entity) {
        super(getMessage(entity));
    }

    private static String getMessage(AbstractEntity entity) {
        return entity.getClass().getSimpleName() + " already exists with id " + entity.getId();
    }
}
