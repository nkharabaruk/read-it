package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends AbstractControllerTest<User> {

    @Override
    protected String getURL() {
        return "/rest/users";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new User();
        entity1.setFirstName("Антоніо");
        entity1.setLastName("Бандерас");
        entity1.setPassword("1234");

        entity2 = new User();
        entity2.setFirstName("Гриць");
        entity2.setLastName("Драпак");
        entity2.setPassword("100500");
    }
}