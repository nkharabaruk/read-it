package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.User;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest extends AbstractControllerTest<User> {

    public UserControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/users";
    }

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        entity = new User();

        // assume that db is empty
        entity.setId(1L);
        entity.setFirstName("Антоніо");
        entity.setLastName("Бандерас");
        entity.setGender(null);
        entity.setDateOfBirth(null);
        entity.setAvatar(null);
        entity.setProfile(null);
        entity.setPassword("1234");
    }
}