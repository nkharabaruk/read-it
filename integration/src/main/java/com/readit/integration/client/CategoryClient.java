package com.readit.integration.client;

import com.readit.rest.dto.CategoryDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "categories", url = "${feign.client.url}/categories")
public interface CategoryClient extends Client<CategoryDTO> { }
