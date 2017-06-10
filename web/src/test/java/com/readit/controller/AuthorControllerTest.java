package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Author;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest extends AbstractControllerTest<Author> {

    public AuthorControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/authors";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Author();

        // assume that db is empty
        entity.setId(1L);
        entity.setFirstName("Тарас");
        entity.setMiddleName("Григорович");
        entity.setLastName("Шевченко");
        entity.setDateOfBirth(LocalDate.of(1814, 3, 9));
        entity.setDateOfDeath(LocalDate.of(1861, 3, 10));
        entity.setBiography("Народився в селі Моринці");

        // initialized to prevent null-[] comparing problems
        entity.setBooks(new ArrayList<>());
        entity.setFiles(new ArrayList<>());
    }
}