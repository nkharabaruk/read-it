package com.readit.integration.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.readit.rest.exception.ApiErrorResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class ApiErrorResponseException extends RuntimeException {
    private static final ObjectMapper mapper = new ObjectMapper();

    private ApiErrorResponse response;

    public ApiErrorResponseException(String body) throws IOException {
        this.response = mapper.readValue(body, ApiErrorResponse.class);
    }
}
