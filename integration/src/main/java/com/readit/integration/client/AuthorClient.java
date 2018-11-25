package com.readit.integration.client;

import com.readit.entity.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "authors", url = "${feign.client.url}/authors")
public interface AuthorClient extends Client<Author> { }
