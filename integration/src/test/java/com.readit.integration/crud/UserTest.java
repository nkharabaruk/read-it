package com.readit.integration.crud;

import com.readit.entity.User;
import com.readit.integration.client.UserClient;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BaseTest<User> {

    @Autowired
    public void setClient(UserClient client) {
        this.client = client;
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new User();
        entity1.setFirstName("Антоніо");
        entity1.setLastName("Бандерас");
        entity1.setEmail("antonio@gmail.com");
        entity1.setPassword("1234");

        entity2 = new User();
        entity2.setFirstName("Гриць");
        entity2.setLastName("Драпак");
        entity2.setEmail("grycko@i.ua");
        entity2.setPassword("100500");
    }
}