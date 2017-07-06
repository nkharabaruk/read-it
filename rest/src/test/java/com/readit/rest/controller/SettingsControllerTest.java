package com.readit.rest.controller;

import com.readit.entity.Settings;
import com.readit.RestApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SettingsControllerTest extends AbstractControllerTest<Settings> {

    @Override
    protected String getURL() {
        return "/rest/settings";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Settings();
        entity.setNotificationAboutNewBook(true);
        entity.setNotificationAboutNewComment(false);
    }
}