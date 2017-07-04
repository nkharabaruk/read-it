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

    @Override
    protected String getURL() {
        return "/rest/profiles";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Profile();
        entity.setSettings(null);
        entity.setWasRead(new ArrayList<>());
        entity.setIsReading(new ArrayList<>());
        entity.setWantToRead(new ArrayList<>());
    }
}