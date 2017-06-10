package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Tag;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TagControllerTest extends AbstractControllerTest<Tag> {

    public TagControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/tags";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Tag();

        // assume that db is empty
        entity.setId(1L);
        entity.setTitle("Книги 2017");
        entity.setCount(7);
    }
}