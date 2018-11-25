package com.readit.integration.client;

import com.readit.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "users", url = "${feign.client.url}/users")
public interface UserClient extends Client<User> { }
