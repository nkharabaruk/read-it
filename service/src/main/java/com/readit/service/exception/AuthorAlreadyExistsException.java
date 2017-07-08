package com.readit.service.exception;

import com.readit.entity.Author;

public class AuthorAlreadyExistsException extends AlreadyExistsException {

    public AuthorAlreadyExistsException(Author author) {
        super(author);
    }

}
