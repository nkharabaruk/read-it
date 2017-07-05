package com.readit.rest.controller;

import com.readit.entity.Tag;
import com.readit.rest.RestApplication;
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

        entity = new Tag();
        entity.setTitle("Книги 2017");
        entity.setCount(7);
    }
}