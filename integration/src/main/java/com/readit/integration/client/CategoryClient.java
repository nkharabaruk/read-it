package com.readit.integration.client;

import com.readit.entity.Category;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "categories", url = "${feign.client.url}/categories")
public interface CategoryClient extends Client<Category> {
}
