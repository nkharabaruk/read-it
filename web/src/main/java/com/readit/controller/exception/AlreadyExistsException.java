package com.readit.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

@ResponseStatus(value = HttpStatus.ALREADY_REPORTED)
public class AlreadyExistsException extends RestClientException {

    public AlreadyExistsException(String msg) {
        super(msg);
    }

    public AlreadyExistsException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
