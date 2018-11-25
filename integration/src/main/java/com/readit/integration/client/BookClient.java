package com.readit.integration.client;

import com.readit.entity.Book;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "books", url = "${feign.client.url}/books")
public interface BookClient extends Client<Book> { }
