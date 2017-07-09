package com.readit.integration.client;


import com.readit.entity.Author;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "authors", url = "${feign.client.url}/authors")
public interface AuthorClient {

    @GetMapping("/{id}")
    Author getById(@PathVariable("id") long id);

    @GetMapping
    List<Author> getAll();
}
