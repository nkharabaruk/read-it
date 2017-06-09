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
import static org.junit.Assert.assertFalse;
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
    public void getAllTest() throws Exception {
        // try to get books when they don`t exist
        List<Book> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertFalse(firstGetResult.contains(book));

        // save books
        Book book1 = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
        Book book2 = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);

        // get books
        List<Book> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertTrue(secondGetResult.contains(book1));
        assertTrue(secondGetResult.contains(book2));

        // delete books
        given().contentType(ContentType.JSON).body(book1)
                .when().delete(URL).then()
                .statusCode(200);
        given().contentType(ContentType.JSON).body(book2)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void getByIdTest() throws Exception {
        // save book
        Book saveResult = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);

        // try to get book
        Book getResult = when().get(URL + "/" + saveResult.getId()).then()
                .statusCode(200).and().extract().as(Book.class);
        assertEquals(saveResult, getResult);

        // delete book
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void saveTest() throws Exception {
        // try to save book
        Book saveResult = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
        assertEquals(book, saveResult);

        // delete book
        given().contentType(ContentType.JSON).body(book)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void deleteAllTest() throws Exception {
        // save books
        Book book1 = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
        Book book2 = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);

        // get books
        List<Book> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertTrue(firstGetResult.contains(book1));
        assertTrue(firstGetResult.contains(book2));

        // delete books
        given().contentType(ContentType.JSON).body(Arrays.asList(book, book))
                .when().delete(URL).then()
                .statusCode(200);

        // try to get books
        List<Book> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertFalse(secondGetResult.contains(book));
    }

    @Test
    public void deleteTest() throws Exception {
        // save book
        Book saveResult = given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
        assertEquals(book, saveResult);

        // delete book
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);

        // try to get books
        List<Book> getResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
        assertFalse(getResult.contains(saveResult));
    }
}