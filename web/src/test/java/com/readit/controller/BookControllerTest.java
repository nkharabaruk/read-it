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
        List<Book> firstGetResult = getBooks();
        assertFalse(firstGetResult.contains(book));

        Book book1 = saveBook();
        Book book2 = saveBook();

        List<Book> secondGetResult = getBooks();
        assertTrue(secondGetResult.contains(book1));
        assertTrue(secondGetResult.contains(book2));

        deleteBooks(book1, book2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Book saveResult = saveBook();

        Book getResult = getBook(saveResult);
        assertEquals(saveResult, getResult);

        deleteBook(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Book saveResult = saveBook();
        assertEquals(book, saveResult);

        deleteBook(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Book book1 = saveBook();
        Book book2 = saveBook();

        List<Book> firstGetResult = Arrays.asList(getBook(book1), getBook(book2));
        assertTrue(firstGetResult.contains(book1));
        assertTrue(firstGetResult.contains(book2));

        deleteBooks(book1, book2);

        List<Book> secondGetResult = getBooks();
        assertFalse(secondGetResult.contains(book1));
        assertFalse(secondGetResult.contains(book2));
    }

    @Test
    public void deleteTest() throws Exception {
        Book saveResult = saveBook();
        assertEquals(book, saveResult);

        deleteBook(saveResult);

        List<Book> getResult = getBooks();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Book> getBooks() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Book[].class));
    }

    private Book getBook(Book book) {
        return when().get(URL + "/" + book.getId()).then()
                .statusCode(200).and().extract().as(Book.class);
    }

    private Book saveBook() {
        return given().contentType(ContentType.JSON).body(book)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Book.class);
    }

    private void deleteBooks(Book...books) {
        given().contentType(ContentType.JSON).body(Arrays.asList(books))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteBook(Book book) {
        given().contentType(ContentType.JSON).body(book)
                .when().delete(URL).then()
                .statusCode(200);
    }
}