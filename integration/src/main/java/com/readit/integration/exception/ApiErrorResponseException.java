package com.readit.integration.exception;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readit.rest.exception.ApiErrorResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class ApiErrorResponseException extends RuntimeException {

    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    private ApiErrorResponse response;

    public ApiErrorResponseException(String body) throws IOException {
        this.response = mapper.readValue(body, ApiErrorResponse.class);
    }
}
