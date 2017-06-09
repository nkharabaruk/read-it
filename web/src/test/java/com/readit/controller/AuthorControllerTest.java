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
        List<Author> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
        assertFalse(firstGetResult.contains(author));

        // save authors
        Author author1 = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);
        Author author2 = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);

        // get authors
        List<Author> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
        assertTrue(secondGetResult.contains(author1));
        assertTrue(secondGetResult.contains(author2));

        // delete authors
        given().contentType(ContentType.JSON).body(author1)
                .when().delete(URL).then()
                .statusCode(200);
        given().contentType(ContentType.JSON).body(author2)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void getByIdTest() throws Exception {
        // save author
        Author saveResult = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);

        // try to get author
        Author getResult = when().get(URL + "/" + saveResult.getId()).then()
                .statusCode(200).and().extract().as(Author.class);
        assertEquals(saveResult, getResult);

        // delete author
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void saveTest() throws Exception {
        // try to save author
        Author saveResult = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);
        assertEquals(author, saveResult);

        // delete author
        given().contentType(ContentType.JSON).body(author)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void deleteAllTest() throws Exception {
        // save authors
        Author author1 = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);
        Author author2 = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);

        // get authors
        List<Author> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
        assertTrue(firstGetResult.contains(author1));
        assertTrue(firstGetResult.contains(author2));

        // delete authors
        given().contentType(ContentType.JSON).body(Arrays.asList(author, author))
                .when().delete(URL).then()
                .statusCode(200);

        // try to get authors
        List<Author> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
        assertFalse(secondGetResult.contains(author));
    }

    @Test
    public void deleteTest() throws Exception {
        // save author
        Author saveResult = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Author.class);
        assertEquals(author, saveResult);

        // delete author
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);

        // try to get authors
        List<Author> getResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Author[].class));
        assertFalse(getResult.contains(saveResult));
    }
}