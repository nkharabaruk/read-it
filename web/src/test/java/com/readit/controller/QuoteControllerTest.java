package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.entity.File;
import com.readit.entity.Quote;
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
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class QuoteControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/quotes";

    private Quote quote;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        quote = new Quote();

        // assume that db is empty
        quote.setId(1L);
        quote.setBook(null);
        quote.setBackground(null);
        quote.setText("Мова завжди живе поряд з піснею, сестрою її рідною.");
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get quotes when they don`t exist
        List<Quote> firstGetResult = getQuotes();
        assertFalse(firstGetResult.contains(quote));

        Quote quote1 = saveQuote();
        Quote quote2 = saveQuote();

        List<Quote> secondGetResult = getQuotes();
        assertTrue(secondGetResult.contains(quote1));
        assertTrue(secondGetResult.contains(quote2));

        deleteQuotes(quote1, quote2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Quote saveResult = saveQuote();

        Quote getResult = getQuote(saveResult);
        assertEquals(saveResult, getResult);

        deleteQuote(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Quote saveResult = saveQuote();
        assertEquals(quote, saveResult);

        deleteQuote(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Quote quote1 = saveQuote();
        Quote quote2 = saveQuote();

        List<Quote> firstGetResult = Arrays.asList(getQuote(quote1), getQuote(quote2));
        assertTrue(firstGetResult.contains(quote1));
        assertTrue(firstGetResult.contains(quote2));

        deleteQuotes(quote1, quote2);

        List<Quote> secondGetResult = getQuotes();
        assertFalse(secondGetResult.contains(quote1));
        assertFalse(secondGetResult.contains(quote2));
    }

    @Test
    public void deleteTest() throws Exception {
        Quote saveResult = saveQuote();
        assertEquals(quote, saveResult);

        deleteQuote(saveResult);

        List<Quote> getResult = getQuotes();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Quote> getQuotes() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Quote[].class));
    }

    private Quote getQuote(Quote quote) {
        return when().get(URL + "/" + quote.getId()).then()
                .statusCode(200).and().extract().as(Quote.class);
    }

    private Quote saveQuote() {
        return given().contentType(ContentType.JSON).body(quote)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Quote.class);
    }

    private void deleteQuotes(Quote...quotes) {
        given().contentType(ContentType.JSON).body(Arrays.asList(quotes))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteQuote(Quote quote) {
        given().contentType(ContentType.JSON).body(quote)
                .when().delete(URL).then()
                .statusCode(200);
    }
}