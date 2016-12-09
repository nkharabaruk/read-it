package com.readit.controller;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.readit.WebApplication;
import com.readit.entity.Author;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorControllerTest {

    @Value("${local.server.port}")
    private int port;

    private ObjectMapper mapper;

    private Author author;

    @Before
    public void setUp() {
        RestAssured.port = port;

        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        author = new Author();
        author.setFirstName("Тарас");
        author.setMiddleName("Григорович");
        author.setLastName("Шевченко");
//        author.setDateOfBirth(LocalDate.of(1814, 3, 9));
//        author.setDateOfDeath(LocalDate.of(1861, 3, 10));
        author.setBiography("Народився в селі Моринці");
    }

    @Test
    public void saveAndGetByIdAndGetAll() throws Exception {
        given().contentType(ContentType.JSON)
                .body(author)
                .when().post("/authors").then()
                .statusCode(200)
                .body(equalToAuthor(author));

        author = when().get("/authors/1").andReturn().as(Author.class);

        when().get("/authors/" + author.getId()).then()
                .statusCode(200)
                .body(equalToAuthor(author));

        when().get("/authors").then()
                .statusCode(200)
                .body(containsAuthor(author));
    }

    private Matcher<Author> equalToAuthor(Author author) {
        return new BaseMatcher<Author>() {
            @Override
            public boolean matches(Object item) {
                Author author1 = null;
                try {
                    // TODO: still fails because of LocalDate
                    author1 = mapper.readValue((String) item, Author.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                boolean result = Objects.equals(author.getFirstName(), author1.getFirstName())
                        && Objects.equals(author.getMiddleName(), author1.getMiddleName())
                        && Objects.equals(author.getLastName(), author1.getLastName())
                        && Objects.equals(author.getDateOfBirth(), author1.getDateOfBirth())
                        && Objects.equals(author.getDateOfDeath(), author1.getDateOfDeath())
                        && Objects.equals(author.getBiography(), author1.getBiography())
                        && Objects.equals(author.getBooks(), author1.getBooks())
                        && Objects.equals(author.getFile(), author1.getFile());
                if (author.getId() != null && author1.getId() != null) {
                    return Objects.equals(author.getId(), author1.getId()) && result;
                } else {
                    return result;
                }
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Authors don't match \n" + author);
            }
        };
    }

    private Matcher<Author> containsAuthor(Author author) {
        return new BaseMatcher<Author>() {
            @Override
            public boolean matches(Object item) {
                List<Author> authors = new ArrayList<>();
                try {
                    authors.addAll(Arrays.asList(mapper.readValue((String) item, Author[].class)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return authors.contains(author);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("List doesn't contain \n" + author);
            }
        };
    }
}