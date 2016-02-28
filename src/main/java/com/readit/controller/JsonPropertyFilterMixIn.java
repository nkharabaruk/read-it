package com.readit.controller;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.stereotype.Component;

@Component
public class JsonPropertyFilterMixIn {

    public String processObject(Object object, String...propertiesToExclude) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Object.class, Filter.class);

        FilterProvider filters = new SimpleFilterProvider()
                .addFilter("filter",
                        SimpleBeanPropertyFilter.serializeAllExcept(
                                propertiesToExclude));
        ObjectWriter writer = mapper.writer(filters);

        return writer.writeValueAsString(object);
    }

    @JsonFilter("filter")
    class Filter {}
}
