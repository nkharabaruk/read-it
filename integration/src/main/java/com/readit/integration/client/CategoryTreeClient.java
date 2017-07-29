package com.readit.integration.client;


import com.readit.rest.dto.CategoryTree;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "categoriesTree", url = "${feign.client.url}/categories/tree")
public interface CategoryTreeClient {

    @GetMapping
    CategoryTree get();

    @GetMapping("/{id}")
    CategoryTree get(@PathVariable("id") long id);
}
