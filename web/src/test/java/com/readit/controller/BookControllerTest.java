package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Book;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Year;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest extends AbstractControllerTest<Book> {

    public BookControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/books";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Book();

        // assume that db is empty
        entity.setTitle("Кобзар");
        entity.setDescription("Збірка поетичних творів Тараса Григоровича Шевченка");
        entity.setYearOfRelease(Year.of(1840));

        // initialized to prevent null-[] comparing problems
        entity.setAuthors(new ArrayList<>());
        entity.setCategories(new ArrayList<>());
        entity.setTags(new ArrayList<>());
    }
}