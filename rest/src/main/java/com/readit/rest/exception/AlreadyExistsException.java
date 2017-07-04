package com.readit.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class AlreadyExistsException extends RestClientException {

    public AlreadyExistsException(String msg) {
        super(msg);
    }

    public AlreadyExistsException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
