package com.readit.rest.exception;

import com.readit.service.exception.AlreadyExistsException;
import com.readit.service.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ApiErrorResponse handleNotFound(HttpServletRequest request, NotFoundException e) {
        // TODO: log
        return new ApiErrorResponse(request, e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(AlreadyExistsException.class)
    public ApiErrorResponse handleAlreadyExists(HttpServletRequest request, AlreadyExistsException e) {
        // TODO: log
        return new ApiErrorResponse(request, e);
    }


}
