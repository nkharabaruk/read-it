package com.readit.service.exception;

import com.readit.entity.Tag;

public class TagNotFoundException extends NotFoundException {
    public TagNotFoundException(long id) {
        super(Tag.class, id);
    }
}
