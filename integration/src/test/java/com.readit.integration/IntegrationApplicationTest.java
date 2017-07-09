package com.readit.integration;

import com.readit.integration.client.AuthorClient;
import com.readit.integration.exception.ApiErrorResponseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IntegrationApplicationTest {

    @Autowired
    private AuthorClient authorClient;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testSmth() {
        System.out.println(authorClient.getById(1L));
        try {
            authorClient.getById(3L);
        } catch (ApiErrorResponseException e) {
            System.out.println(e.getResponse());
        }

    }
}