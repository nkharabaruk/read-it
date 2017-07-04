package com.readit.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RestClientException {

    public NotFoundException(String msg) {
        super(msg);
    }

    public NotFoundException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
