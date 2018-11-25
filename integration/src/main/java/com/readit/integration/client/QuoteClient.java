package com.readit.integration.client;

import com.readit.entity.Quote;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

@Service
@FeignClient(name = "quotes", url = "${feign.client.url}/quotes")
public interface QuoteClient extends Client<Quote> { }
