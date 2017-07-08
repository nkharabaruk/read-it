package com.readit.service.exception;

import com.readit.entity.User;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(long id) {
        super(User.class, id);
    }
}
