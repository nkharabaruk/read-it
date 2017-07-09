package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class NotFoundException extends RuntimeException {
    private static final String MESSAGE = "%s with %s = %s doesn't exist";


    NotFoundException(Class<? extends AbstractEntity> type, long id) {
        super(getMessage(type, "id", id));
    }

    NotFoundException(Class<? extends AbstractEntity> type, String field, Object value) {
        super(getMessage(type, field, value));
    }

    private static String getMessage(Class<? extends AbstractEntity> type, String field, Object value) {
        return String.format(MESSAGE, type.getSimpleName(), field, String.valueOf(value));
    }
}
