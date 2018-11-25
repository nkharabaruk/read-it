package com.readit.integration.client;

import com.readit.entity.Settings;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "settings", url = "${feign.client.url}/settings")
public interface SettingsClient extends Client<Settings> { }
