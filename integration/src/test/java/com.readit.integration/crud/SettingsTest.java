package com.readit.integration.crud;

import com.readit.entity.Settings;
import com.readit.integration.client.SettingsClient;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class SettingsTest extends BaseTest<Settings> {

    @Autowired
    public void setClient(SettingsClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Settings();
        entity1.setNotificationAboutNewBook(true);
        entity1.setNotificationAboutNewComment(false);

        entity2 = new Settings();
        entity2.setNotificationAboutNewBook(false);
        entity2.setNotificationAboutNewComment(true);
    }

    @Override
    public void saveDuplicateTest() {
        // nothing to do here
    }
}