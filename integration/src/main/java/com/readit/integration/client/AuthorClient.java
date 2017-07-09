package com.readit.integration.client;


import com.readit.entity.Author;
import feign.Param;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@FeignClient(name = "authors", url = "${feign.client.url}/authors")
public interface AuthorClient {

    @GetMapping("/{id}")
    Author getById(@Param("id") long id);

    @GetMapping
    List<Author> getAll();
}
