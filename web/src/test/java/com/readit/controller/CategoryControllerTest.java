package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Category;
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
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/categories";

    private Category category;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        category = new Category();

        // assume that db is empty
        category.setId(1L);
        category.setName("Фантастика");

        // initialized to prevent null-[] comparing problems
        category.setParent(null);
        category.setChildren(new ArrayList<>());
        category.setBooks(new ArrayList<>());
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get categories when they don`t exist
        List<Category> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Category[].class));
        assertFalse(firstGetResult.contains(category));

        // save categories
        Category category1 = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);
        Category category2 = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);

        // get categories
        List<Category> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Category[].class));
        assertTrue(secondGetResult.contains(category1));
        assertTrue(secondGetResult.contains(category2));

        // delete categories
        given().contentType(ContentType.JSON).body(category1)
                .when().delete(URL).then()
                .statusCode(200);
        given().contentType(ContentType.JSON).body(category2)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void getByIdTest() throws Exception {
        // save category
        Category saveResult = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);

        // try to get category
        Category getResult = when().get(URL + "/" + saveResult.getId()).then()
                .statusCode(200).and().extract().as(Category.class);
        assertEquals(saveResult, getResult);

        // delete category
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void saveTest() throws Exception {
        // try to save category
        Category saveResult = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);
        assertEquals(category, saveResult);

        // delete category
        given().contentType(ContentType.JSON).body(category)
                .when().delete(URL).then()
                .statusCode(200);
    }

    @Test
    public void deleteAllTest() throws Exception {
        // save categories
        Category category1 = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);
        Category category2 = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);

        // get categories
        List<Category> firstGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Category[].class));
        assertTrue(firstGetResult.contains(category1));
        assertTrue(firstGetResult.contains(category2));

        // delete categories
        given().contentType(ContentType.JSON).body(Arrays.asList(category, category))
                .when().delete(URL).then()
                .statusCode(200);

        // try to get categories
        List<Category> secondGetResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Category[].class));
        assertFalse(secondGetResult.contains(category));
    }

    @Test
    public void deleteTest() throws Exception {
        // save category
        Category saveResult = given().contentType(ContentType.JSON).body(category)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Category.class);
        assertEquals(category, saveResult);

        // delete category
        given().contentType(ContentType.JSON).body(saveResult)
                .when().delete(URL).then()
                .statusCode(200);

        // try to get categories
        List<Category> getResult = Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Category[].class));
        assertFalse(getResult.contains(saveResult));
    }
}