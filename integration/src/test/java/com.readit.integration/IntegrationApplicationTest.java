package com.readit.integration;

import com.readit.integration.client.AuthorClient;
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
        System.out.println(authorClient.getAll());
    }
}