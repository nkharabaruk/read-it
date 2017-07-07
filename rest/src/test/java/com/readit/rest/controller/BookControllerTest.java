package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.Book;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Year;

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

        entity1 = new Book();
        entity1.setTitle("Кобзар");
        entity1.setDescription("Збірка поетичних творів Тараса Григоровича Шевченка");
        entity1.setYearOfRelease(Year.of(1840));

        entity2 = new Book();
        entity2.setTitle("Фарбований лис");
        entity2.setDescription("Казка для дітей про Лисовичі");
        entity2.setYearOfRelease(Year.of(1890));
    }
}