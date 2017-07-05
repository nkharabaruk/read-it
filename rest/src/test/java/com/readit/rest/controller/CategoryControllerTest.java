package com.readit.rest.controller;

import com.readit.entity.Category;
import com.readit.rest.RestApplication;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@Ignore
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

        entity = new Category();
        entity.setName("Фантастика");
        entity.setParent(null);
        entity.setChildren(new ArrayList<>());
        entity.setBooks(new ArrayList<>());
    }
}