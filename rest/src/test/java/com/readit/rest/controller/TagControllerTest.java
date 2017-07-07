package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.Tag;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerTest extends AbstractControllerTest<Tag> {

    @Override
    protected String getURL() {
        return "/rest/tags";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Tag();
        entity1.setTitle("Книги 2017");
        entity1.setCount(7);

        entity2 = new Tag();
        entity2.setTitle("Казки");
        entity2.setCount(17);
    }
}