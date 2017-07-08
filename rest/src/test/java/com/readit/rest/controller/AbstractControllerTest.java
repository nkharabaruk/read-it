package com.readit.rest.controller;

import com.readit.RestApplication;
import com.readit.entity.AbstractEntity;
import com.readit.rest.exception.ApiErrorResponse;
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

    private static final long NOT_EXISTING_ID = 100500;
    private static final long SECOND = 1000;
    private static final int NOT_FOUND_EXCEPTION_STATUS = 404;
    private static final String NOT_FOUND_EXCEPTION_CLASS = "com.readit.service.exception.%sNotFoundException";
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "%s with id = %d doesn't exist";
    private static final int ALREADY_EXIST_EXCEPTION_STATUS = 409;
    private static final String ALREADY_EXIST_EXCEPTION_CLASS = "com.readit.service.exception.%sAlreadyExistsException";;
    private static final String ALREADY_EXIST_EXCEPTION_MESSAGE = "%s already exists. %s";

    T entity1;
    T entity2;

    private final Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractControllerTest.class);
    private final Class<? extends AbstractEntity[]> entityTypeArray = ((T[]) Array.newInstance(entityType, 0)).getClass();

    protected abstract String getURL();

    private String getURL(long id) {
        return getURL() + "/" + id;
    }

    @Value("${local.server.port}")
    protected int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;
        delete();
    }

    @Test
    public void getByIdTest() {
        long id = save(entity1).getId();

        T result = get(id);

        assertEquals(entity1, result);
    }

    @Test
    public void getByIdNotExisting() {
        ApiErrorResponse response = getNotExisting(NOT_EXISTING_ID);

        verifyNotFoundResponse(response);
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
    public void saveTest() throws Exception {
        T result = save(entity1);

        assertEquals(entity1, result);
    }

    @Test
    public void saveDuplicateTest() {
        T entity = save(entity1);

        ApiErrorResponse response = saveDuplicate(entity1);

        verifyDuplicateResponse(response, entity);
    }

    @Test
    public void deleteTest() {
        T savedEntity = save(entity1);
        delete(savedEntity.getId());

        List<T> getResult = getAll();

        assertFalse(getResult.contains(savedEntity));
    }

    @Test
    public void deleteNotExistingTest() {
        ApiErrorResponse response = deleteNotExisting(NOT_EXISTING_ID);

        verifyNotFoundResponse(response);
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

    private ApiErrorResponse getNotExisting(long id) {
        return when().get(getURL() + "/" + id).then()
                .statusCode(404).and().extract().as(ApiErrorResponse.class);
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

    private ApiErrorResponse saveDuplicate(T entity) {
        return given().contentType(ContentType.JSON).body(entity)
                .when().post(getURL()).then()
                .statusCode(409).and().extract().as(ApiErrorResponse.class);
    }

    private void delete(long id) {
        given().contentType(ContentType.JSON)
                .when().delete(getURL() + "/" + id).then()
                .statusCode(200);
    }

    private ApiErrorResponse deleteNotExisting(long id) {
        return when().delete(getURL() + "/" + id).then()
                .statusCode(404).and().extract().as(ApiErrorResponse.class);
    }

    private void delete() {
        when().delete(getURL()).then()
                .statusCode(200);
    }

    private void verifyNotFoundResponse(ApiErrorResponse result) {
        assertEquals(NOT_FOUND_EXCEPTION_STATUS, result.getStatus());
        assertEquals(String.format(NOT_FOUND_EXCEPTION_CLASS, entityType.getSimpleName()), result.getException());
        assertEquals(getURL(NOT_EXISTING_ID), result.getPath());
        assertEquals(String.format(NOT_FOUND_EXCEPTION_MESSAGE, entityType.getSimpleName(), NOT_EXISTING_ID), result.getMessage());
        assertTrue(System.currentTimeMillis() - result.getTimestamp() <= SECOND);
    }

    private void verifyDuplicateResponse(ApiErrorResponse result, T entity) {
        assertEquals(ALREADY_EXIST_EXCEPTION_STATUS, result.getStatus());
        assertEquals(String.format(ALREADY_EXIST_EXCEPTION_CLASS, entityType.getSimpleName()), result.getException());
        assertEquals(getURL(), result.getPath());
        assertEquals(String.format(ALREADY_EXIST_EXCEPTION_MESSAGE, entityType.getSimpleName(), entity.toString()), result.getMessage());
        assertTrue(System.currentTimeMillis() - result.getTimestamp() <= SECOND);
    }
}
