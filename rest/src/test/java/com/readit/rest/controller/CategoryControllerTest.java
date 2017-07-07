package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.Category;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends AbstractControllerTest<Category> {

    @Override
    protected String getURL() {
        return "/rest/categories";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity1 = new Category();
        entity1.setName("Українська");
        entity1.setParent(null);

        entity2 = new Category();
        entity2.setName("Фантастика");
        entity2.setParent(entity1);
    }
}