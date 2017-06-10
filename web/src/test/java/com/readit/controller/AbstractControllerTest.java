package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.AbstractEntity;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.GenericTypeResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest<T extends AbstractEntity> {

    protected T entity;
    private final Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractControllerTest.class);

    protected abstract String getURL();

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get entities when they don`t exist
        List<T> firstGetResult = getEntities();
        assertFalse(firstGetResult.contains(entity));

        T entity1 = saveEntity(entity);
        T entity2 = saveEntity(entity);

        List<T> secondGetResult = getEntities();
        assertTrue(secondGetResult.contains(entity1));
        assertTrue(secondGetResult.contains(entity2));

        deleteEntity(entity1, entity2);
    }

    @Test
    public void getByIdTest() throws Exception {
        T saveResult = saveEntity(entity);

        T getResult = getEntity(saveResult);
        assertEquals(saveResult, getResult);

        deleteEntity(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        T saveResult = saveEntity(entity);
        assertEquals(entity, saveResult);

        deleteEntity(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        T entity1 = saveEntity(entity);
        T entity2 = saveEntity(entity);

        List<T> firstGetResult = Arrays.asList(getEntity(entity1), getEntity(entity2));
        assertTrue(firstGetResult.contains(entity1));
        assertTrue(firstGetResult.contains(entity2));

        deleteEntity(entity1, entity2);

        List<T> secondGetResult = getEntities();
        assertFalse(secondGetResult.contains(entity1));
        assertFalse(secondGetResult.contains(entity2));
    }

    @Test
    public void deleteTest() throws Exception {
        T saveResult = saveEntity(entity);
        assertEquals(entity, saveResult);

        deleteEntity(saveResult);

        List<T> getResult = getEntities();
        assertFalse(getResult.contains(saveResult));
    }

    private List<T> getEntities() {
        return Arrays.asList((T[]) when().get(getURL()).then()
                .statusCode(200).and().extract().as(((T[]) Array.newInstance(entityType, 0)).getClass()));
    }

    private T getEntity(T entity) throws ClassNotFoundException {
        return when().get(getURL() + "/" + entity.getId()).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private T saveEntity(T entity) throws ClassNotFoundException {
        return given().contentType(ContentType.JSON).body(entity)
                .when().post(getURL()).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private void deleteEntity(T... entities) {
        given().contentType(ContentType.JSON).body(Arrays.asList(entities))
                .when().delete(getURL() + "/all").then()
                .statusCode(200);
    }

    private void deleteEntity(T entity) {
        given().contentType(ContentType.JSON).body(entity)
                .when().delete(getURL()).then()
                .statusCode(200);
    }
}
