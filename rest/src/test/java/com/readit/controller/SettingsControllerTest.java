package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Settings;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
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