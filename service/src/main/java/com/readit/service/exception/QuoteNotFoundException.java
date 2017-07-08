package com.readit.service.exception;

import com.readit.entity.Quote;

public class QuoteNotFoundException extends NotFoundException {
    public QuoteNotFoundException(long id) {
        super(Quote.class, id);
    }
}
