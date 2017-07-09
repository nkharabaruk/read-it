package com.readit.service.exception;

import com.readit.entity.Tag;

public class TagAlreadyExistsException extends AlreadyExistsException {
    public TagAlreadyExistsException(Tag tag) {
        super(tag);
    }
}
