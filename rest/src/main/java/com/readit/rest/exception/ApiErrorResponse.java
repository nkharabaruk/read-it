package com.readit.rest.exception;

import com.readit.service.exception.AlreadyExistsException;
import com.readit.service.exception.NotFoundException;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

@Data
public class ApiErrorResponse {
    private long timestamp;
    private int status;
    private String exception;
    private String message;
    private String path;

    public ApiErrorResponse(HttpServletRequest request, NotFoundException e) {
        this(request, (Exception) e);
        this.status = HttpStatus.NOT_FOUND.value();
    }

    public ApiErrorResponse(HttpServletRequest request, AlreadyExistsException e) {
        this(request, (Exception) e);
        this.status = HttpStatus.CONFLICT.value();
    }

    public ApiErrorResponse(HttpServletRequest request, Exception e) {
        this.timestamp = System.currentTimeMillis();
        this.exception = e.getClass().getName();
        this.message = e.getMessage();
        this.path = request.getServletPath();
    }
}
