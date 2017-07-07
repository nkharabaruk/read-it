package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.Profile;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProfileControllerTest extends AbstractControllerTest<Profile> {

    @Override
    protected String getURL() {
        return "/rest/profiles";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Profile();
        entity1.setSettings(null);
        entity1.setWasRead(new ArrayList<>());
        entity1.setIsReading(new ArrayList<>());
        entity1.setWantToRead(new ArrayList<>());

        entity2 = new Profile();
        entity2.setSettings(null);
        entity2.setWasRead(new ArrayList<>());
        entity2.setIsReading(new ArrayList<>());
        entity2.setWantToRead(new ArrayList<>());
    }
}