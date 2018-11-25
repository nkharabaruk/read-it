package com.readit.integration.client;

import com.readit.entity.Profile;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "profiles", url = "${feign.client.url}/profiles")
public interface ProfileClient extends Client<Profile> { }
