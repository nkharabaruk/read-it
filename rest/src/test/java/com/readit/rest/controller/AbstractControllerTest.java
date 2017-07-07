package com.readit.rest.controller;

import com.readit.RestApplication;
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
@SpringBootTest(classes = RestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractControllerTest<T extends AbstractEntity> {

    T entity1;
    T entity2;

    private final Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractControllerTest.class);
    private final Class<? extends AbstractEntity[]> entityTypeArray = ((T[]) Array.newInstance(entityType, 0)).getClass();

    protected abstract String getURL();

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        delete();
    }

    @Test
    public void getAllTest() throws Exception {
        save(entity1);
        save(entity2);

        List<T> result = getAll();

        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
    }

    @Test
    public void getByIdTest() {
        long id = save(entity1).getId();

        T result = get(id);

        assertEquals(entity1, result);
    }

    @Test
    public void saveTest() throws Exception {
        T result = save(entity1);

        assertEquals(entity1, result);
    }

    @Test
    public void deleteTest() {
        T savedEntity = save(entity1);
        delete(savedEntity.getId());

        List<T> getResult = getAll();

        assertFalse(getResult.contains(savedEntity));
    }

    @Test
    public void deleteAllTest() {
        T savedEntity1 = save(entity1);
        T savedEntity2 = save(entity2);

        delete();

        List<T> result = getAll();
        assertFalse(result.contains(savedEntity1));
        assertFalse(result.contains(savedEntity2));
    }

    private T get(long id) {
        return when().get(getURL() + "/" + id).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private List<T> getAll() {
        return Arrays.asList((T[]) when().get(getURL()).then()
                .statusCode(200).and().extract().as(entityTypeArray));
    }

    private T save(T entity) {
        return given().contentType(ContentType.JSON).body(entity)
                .when().post(getURL()).then()
                .statusCode(200).and().extract().as(entityType);
    }

    private void delete(long id) {
        given().contentType(ContentType.JSON)
                .when().delete(getURL() + "/" + id).then()
                .statusCode(200);
    }

    private void delete() {
        when().delete(getURL()).then()
                .statusCode(200);
    }

    private Exception getNotExisting(T entity) {
        // TODO: fix it
        return when().get(getURL() + "/" + entity.getId()).then()
                .statusCode(404).and().extract().as(Exception.class);
    }

    // TODO: fix this
    private Exception deleteNotExisting(T entity) {
        return given().contentType(ContentType.JSON).body(entity)
                .when().delete(getURL()).then()
                .statusCode(404).and().extract().as(Exception.class);
    }
}
