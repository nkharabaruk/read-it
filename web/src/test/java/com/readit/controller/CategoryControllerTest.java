package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Category;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryControllerTest extends AbstractControllerTest<Category> {

    public CategoryControllerTest() {
        super();
    }

    @Override
    protected String getURL() {
        return "/categories";
    }

    @Before
    public void setUp() {
        super.setUp();

        entity = new Category();

        // assume that db is empty
        entity.setId(1L);
        entity.setName("Фантастика");

        // initialized to prevent null-[] comparing problems
        entity.setParent(null);
        entity.setChildren(new ArrayList<>());
        entity.setBooks(new ArrayList<>());
    }
}