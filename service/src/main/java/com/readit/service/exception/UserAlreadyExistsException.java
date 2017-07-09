package com.readit.service.exception;

import com.readit.entity.User;

public class UserAlreadyExistsException extends AlreadyExistsException {

    public UserAlreadyExistsException(User entity) {
        super(entity);
    }
}
