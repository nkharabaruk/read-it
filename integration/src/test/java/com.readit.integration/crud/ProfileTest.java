package com.readit.integration.crud;

import com.readit.entity.Profile;
import com.readit.integration.client.ProfileClient;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ProfileTest extends BaseTest<Profile> {

    @Autowired
    public void setClient(ProfileClient client) {
        this.client = client;
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

    @Override
    public void saveDuplicateTest() {
        // nothing to do here
    }
}