package com.readit.integration.client;

import com.readit.entity.Tag;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "tags", url = "${feign.client.url}/tags")
public interface TagClient extends Client<Tag> {
}
