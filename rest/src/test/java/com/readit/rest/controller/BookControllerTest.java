package com.readit.rest.controller;

import com.readit.entity.Book;
import com.readit.RestApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Year;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest extends AbstractControllerTest<Book> {

    @Override
    protected String getURL() {
        return "/rest/books";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Book();
        entity.setTitle("Кобзар");
        entity.setDescription("Збірка поетичних творів Тараса Григоровича Шевченка");
        entity.setYearOfRelease(Year.of(1840));
        entity.setAuthors(new ArrayList<>());
        entity.setCategories(new ArrayList<>());
        entity.setTags(new ArrayList<>());
    }
}