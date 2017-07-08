package com.readit.service.exception;

import com.readit.entity.Profile;

public class ProfileNotFoundException extends NotFoundException {

    public ProfileNotFoundException(long id) {
        super(Profile.class, id);
    }
}
