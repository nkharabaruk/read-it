package com.readit.controller;

import com.readit.WebApplication;
import com.readit.controller.exception.NotFoundException;
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

    T entity;
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
        List<T> firstGetResult = getAll();
        assertFalse(firstGetResult.contains(entity));

        T entity1 = save(entity);
        T entity2 = save(entity);

        List<T> secondGetResult = getAll();
        assertTrue(secondGetResult.contains(entity1));
        assertTrue(secondGetResult.contains(entity2));

        delete(entity1, entity2);
    }

    @Test
    public void getByIdTest() {
        Exception exceptionResult = getNotExisting(entity);
        assertEquals(exceptionResult.getClass(), NotFoundException.class);
        assertTrue(exceptionResult instanceof NotFoundException);

        T saveResult = save(entity);

        T getResult2 = get(saveResult);
        assertEquals(saveResult, getResult2);

        delete(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        // TODO: add saveNotExisting test

        T saveResult = save(entity);
        assertEquals(entity, saveResult);

        delete(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        T entity1 = save(entity);
        T entity2 = save(entity);

        List<T> firstGetResult = Arrays.asList(get(entity1), get(entity2));
        assertTrue(firstGetResult.contains(entity1));
        assertTrue(firstGetResult.contains(entity2));

        delete(entity1, entity2);

        List<T> secondGetResult = getAll();
        assertFalse(secondGetResult.contains(entity1));
        assertFalse(secondGetResult.contains(entity2));
    }

    @Test
    public void deleteTest() {
        Exception exceptionResult = deleteNotExisting(entity);
        assertEquals(exceptionResult.getClass(), NotFoundException.class);
        assertTrue(exceptionResult instanceof NotFoundException);

        T saveResult = save(entity);
        assertEquals(entity, saveResult);

        delete(saveResult);

        List<T> getResult = getAll();
        assertFalse(getResult.contains(saveResult));
    }

    private List<T> getAll() {
        return Arrays.asList((T[]) when().get(getURL()).then()
                .statusCode(200).and().extract().as(((T[]) Array.newInstance(entityType, 0)).getClass()));
    }

    private T get(T entity) {
        return when().get(getURL() + "/" + entity.getId()).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private NotFoundException getNotExisting(T entity) {
        return when().get(getURL() + "/" + entity.getId()).then()
                .statusCode(404).and().extract().as(NotFoundException.class);
    }

    private T save(T entity) {
        return given().contentType(ContentType.JSON).body(entity)
                .when().post(getURL()).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private void delete(T... entities) {
        given().contentType(ContentType.JSON).body(Arrays.asList(entities))
                .when().delete(getURL() + "/all").then()
                .statusCode(200);
    }

    private void delete(T entity) {
        given().contentType(ContentType.JSON).body(entity)
                .when().delete(getURL()).then()
                .statusCode(200);
    }

    // TODO: fix this
    private NotFoundException deleteNotExisting(T entity) {
        return given().contentType(ContentType.JSON).body(entity)
                .when().delete(getURL()).then()
                .statusCode(404).and().extract().as(NotFoundException.class);
    }
}
