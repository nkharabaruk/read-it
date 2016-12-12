package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Book;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/books";

    private Book book;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        book = new Book();

        // assume that db is empty
        book.setId(1L);
        book.setTitle("Кобзар");
        book.setDescription("Збірка поетичних творів Тараса Григоровича Шевченка");
        book.setYearOfRelease(Year.of(1840));

        // initialized to prevent null-[] comparing problems
        book.setAuthors(new ArrayList<>());
        book.setCategories(new ArrayList<>());
        book.setTags(new ArrayList<>());
    }

    @Test
    public void saveTest() throws Exception {
        Book result = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
        assertEquals(book, result);
    }

    @Test
    public void getByIdTest() throws Exception {
        Book result = when().get(URL + "/" + book.getId()).then()
                .statusCode(200).and().extract().as(Book.class);
        assertEquals(book, result);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Book> result = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertTrue(result.contains(book));
    }
}