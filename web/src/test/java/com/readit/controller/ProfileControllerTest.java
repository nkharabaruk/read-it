package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Profile;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest extends AbstractControllerTest<Profile> {

    public ProfileControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/profiles";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Profile();

        // assume that db is empty
        entity.setId(1L);
        entity.setSettings(null);

        // initialized to prevent null-[] comparing problems
        entity.setWasRead(new ArrayList<>());
        entity.setIsReading(new ArrayList<>());
        entity.setWantToRead(new ArrayList<>());
    }
}