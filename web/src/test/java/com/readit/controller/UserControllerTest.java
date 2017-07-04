package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.User;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends AbstractControllerTest<User> {

    @Override
    protected String getURL() {
        return "/rest/users";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new User();
        entity.setFirstName("Антоніо");
        entity.setLastName("Бандерас");
        entity.setGender(null);
        entity.setDateOfBirth(null);
        entity.setAvatar(null);
        entity.setProfile(null);
        entity.setPassword("1234");
    }
}