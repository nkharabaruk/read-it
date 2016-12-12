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
    public void saveTest() throws Exception {
        Author result = given().contentType(ContentType.JSON).body(author)
                .when().post(URL).then()
                .statusCode(200).extract().as(Author.class);
        assertEquals(author, result);
    }

    @Test
    public void getByIdTest() throws Exception {
        Author result = when().get(URL + "/" + author.getId()).then()
                .statusCode(200).extract().as(Author.class);
        assertEquals(author, result);
    }

    @Test
    public void getAllTest() throws Exception {
        List<Author> result = Arrays.asList(when().get(URL).then()
                .statusCode(200).extract().as(Author[].class));
        assertTrue(result.contains(author));
    }
}