package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class NotFoundException extends RuntimeException {

    NotFoundException(Class<? extends AbstractEntity> type, long id) {
        super(getMessage(type, id));
    }

    private static String getMessage(Class<? extends AbstractEntity> type, long id) {
        return type.getSimpleName() + " with id = " + id + " doesn't exist";
    }
}
