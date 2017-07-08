package com.readit.service.exception;

import com.readit.entity.Book;

public class BookNotFoundException extends NotFoundException {

    public BookNotFoundException(long id) {
        super(Book.class, id);
    }
}
