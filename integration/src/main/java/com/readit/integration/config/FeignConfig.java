package com.readit.integration.config;

import com.readit.integration.exception.ApiErrorResponseException;
import feign.Util;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class FeignConfig {

    @Bean
    public ErrorDecoder errorDecoder() {
        return (methodKey, response) -> {
            try {
                String body = Util.toString(response.body().asReader());
                return new ApiErrorResponseException(body);
            } catch (IOException e) {
                return null;
            }
        };
    }

}
