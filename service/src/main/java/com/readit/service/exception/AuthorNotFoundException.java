package com.readit.service.exception;

import com.readit.entity.Author;

public class AuthorNotFoundException extends NotFoundException {

    public AuthorNotFoundException(long id) {
        super(Author.class, id);
    }
}
