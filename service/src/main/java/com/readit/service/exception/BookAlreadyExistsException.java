package com.readit.service.exception;

import com.readit.entity.AbstractEntity;

public class BookAlreadyExistsException extends AlreadyExistsException {

    public BookAlreadyExistsException(AbstractEntity entity) {
        super(entity);
    }
}
