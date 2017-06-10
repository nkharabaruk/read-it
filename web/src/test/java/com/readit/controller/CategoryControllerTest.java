package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Category;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends AbstractControllerTest<Category> {

    @Override
    protected String getURL() {
        return "/categories";
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