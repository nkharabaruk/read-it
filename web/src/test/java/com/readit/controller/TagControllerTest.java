package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Tag;
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
public class TagControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/tags";

    private Tag tag;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        tag = new Tag();

        // assume that db is empty
        tag.setId(1L);
        tag.setTitle("Книги 2017");
        tag.setCount(7);
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get tags when they don`t exist
        List<Tag> firstGetResult = getTags();
        assertFalse(firstGetResult.contains(tag));

        Tag tag1 = saveTag();
        Tag tag2 = saveTag();

        List<Tag> secondGetResult = getTags();
        assertTrue(secondGetResult.contains(tag1));
        assertTrue(secondGetResult.contains(tag2));

        deleteTags(tag1, tag2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Tag saveResult = saveTag();

        Tag getResult = getTag(saveResult);
        assertEquals(saveResult, getResult);

        deleteTag(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Tag saveResult = saveTag();
        assertEquals(tag, saveResult);

        deleteTag(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Tag tag1 = saveTag();
        Tag tag2 = saveTag();

        List<Tag> firstGetResult = Arrays.asList(getTag(tag1), getTag(tag2));
        assertTrue(firstGetResult.contains(tag1));
        assertTrue(firstGetResult.contains(tag2));

        deleteTags(tag1, tag2);

        List<Tag> secondGetResult = getTags();
        assertFalse(secondGetResult.contains(tag1));
        assertFalse(secondGetResult.contains(tag2));
    }

    @Test
    public void deleteTest() throws Exception {
        Tag saveResult = saveTag();
        assertEquals(tag, saveResult);

        deleteTag(saveResult);

        List<Tag> getResult = getTags();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Tag> getTags() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Tag[].class));
    }

    private Tag getTag(Tag tag) {
        return when().get(URL + "/" + tag.getId()).then()
                .statusCode(200).and().extract().as(Tag.class);
    }

    private Tag saveTag() {
        return given().contentType(ContentType.JSON).body(tag)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Tag.class);
    }

    private void deleteTags(Tag...tags) {
        given().contentType(ContentType.JSON).body(Arrays.asList(tags))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteTag(Tag tag) {
        given().contentType(ContentType.JSON).body(tag)
                .when().delete(URL).then()
                .statusCode(200);
    }
}