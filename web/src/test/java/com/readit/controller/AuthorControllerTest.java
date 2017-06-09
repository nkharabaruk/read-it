package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Author;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
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
public class AuthorControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/authors";

    private Author author;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        author = new Author();

        // assume that db is empty
        author.setId(1L);
        author.setFirstName("Тарас");
        author.setMiddleName("Григорович");
        author.setLastName("Шевченко");
        author.setDateOfBirth(LocalDate.of(1814, 3, 9));
        author.setDateOfDeath(LocalDate.of(1861, 3, 10));
        author.setBiography("Народився в селі Моринці");

        // initialized to prevent null-[] comparing problems
        author.setBooks(new ArrayList<>());
        author.setFiles(new ArrayList<>());
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get authors when they don`t exist
        List<Author> firstGetResult = getAuthors();
        assertFalse(firstGetResult.contains(author));

        Author author1 = saveAuthor();
        Author author2 = saveAuthor();

        List<Author> secondGetResult = getAuthors();
        assertTrue(secondGetResult.contains(author1));
        assertTrue(secondGetResult.contains(author2));

        deleteAuthors(author1, author2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Author saveResult = saveAuthor();

        Author getResult = getAuthor(saveResult);
        assertEquals(saveResult, getResult);

        deleteAuthor(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Author saveResult = saveAuthor();
        assertEquals(author, saveResult);

        deleteAuthor(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Author author1 = saveAuthor();
        Author author2 = saveAuthor();

        List<Author> firstGetResult = Arrays.asList(getAuthor(author1), getAuthor(author2));
        assertTrue(firstGetResult.contains(author1));
        assertTrue(firstGetResult.contains(author2));

        deleteAuthors(author1, author2);

        List<Author> secondGetResult = getAuthors();
        assertFalse(secondGetResult.contains(author1));
        assertFalse(secondGetResult.contains(author2));
    }

    @Test
    public void deleteTest() throws Exception {
        Author saveResult = saveAuthor();
        assertEquals(author, saveResult);

        deleteAuthor(saveResult);

        List<Author> getResult = getAuthors();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Author> getAuthors() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
    }

    private Author getAuthor(Author author) {
        return when().get(URL + "/" + author.getId()).then()
                .statusCode(200).and().extract().as(Author.class);
    }

    private Author saveAuthor() {
        return given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);
    }

    private void deleteAuthors(Author...authors) {
        given().contentType(ContentType.JSON).body(Arrays.asList(authors))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteAuthor(Author author) {
        given().contentType(ContentType.JSON).body(author)
                .when().delete(URL).then()
                .statusCode(200);
    }
}