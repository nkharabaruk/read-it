package com.readit.rest.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {

    @GetMapping("/**")
    public ClassPathResource redirectToIndex() {
        return new ClassPathResource("static/index.html");
    }

}
