package com.readit.integration.crud;

import com.readit.entity.AbstractEntity;
import com.readit.integration.client.Client;
import com.readit.integration.exception.ApiErrorResponseException;
import com.readit.rest.exception.ApiErrorResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.GenericTypeResolver;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Array;
import java.util.List;

import static org.junit.Assert.*;

@SuppressWarnings("unchecked")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class BaseTest<T extends AbstractEntity> {
    private static final long NOT_EXISTING_ID = 100500;
    private static final long SECOND = 1000;
    private static final int NOT_FOUND_EXCEPTION_STATUS = 404;
    private static final String NOT_FOUND_EXCEPTION_CLASS = "com.readit.service.exception.%sNotFoundException";
    private static final String NOT_FOUND_EXCEPTION_MESSAGE = "%s with id = %d doesn't exist";
    private static final int ALREADY_EXIST_EXCEPTION_STATUS = 409;
    private static final String ALREADY_EXIST_EXCEPTION_CLASS = "com.readit.service.exception.%sAlreadyExistsException";
    private static final String ALREADY_EXIST_EXCEPTION_MESSAGE = "%s already exists with id %d";
    private static final String DTO = "DTO";
    private static final String EMPTY_STRING = "";

    private final Class<T> entityType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), BaseTest.class);
    private final Class<? extends AbstractEntity[]> entityTypeArray = ((T[]) Array.newInstance(entityType, 0)).getClass();

    T entity1;
    T entity2;

    Client<T> client;

    @Before
    public void setUp() {
        client.delete();
    }

    @Test
    public void getByIdTest() {
        long id = client.save(entity1).getId();

        T result = client.get(id);

        assertEquals(entity1, result);
    }

    @Test
    public void getByIdNotExisting() {
        ApiErrorResponse response = null;
        try {
            client.get(NOT_EXISTING_ID);
        } catch (ApiErrorResponseException e) {
            response = e.getResponse();
        }
        verifyNotFoundResponse(response);
    }

    @Test
    public void getAllTest() {
        client.save(entity1);
        client.save(entity2);

        List<T> result = client.get();

        assertTrue(result.contains(entity1));
        assertTrue(result.contains(entity2));
    }

    @Test
    public void saveTest() {
        T result = client.save(entity1);

        assertEquals(entity1, result);
    }

    @Test
    public void saveDuplicateTest() {
        T entity = client.save(entity1);
        ApiErrorResponse response = null;
        try {
            client.save(entity1);
        } catch (ApiErrorResponseException e) {
            response = e.getResponse();
        }
        verifyDuplicateResponse(response, entity);

    }

    @Test
    public void updateTest() {
        T saved = client.save(entity1);

        T updated = client.update(saved.getId(), entity2);

        assertEquals(entity2, updated);
    }

    @Test
    public void updateNotExistingTest() {
        ApiErrorResponse response = null;
        try {
            client.update(NOT_EXISTING_ID, entity1);
        } catch (ApiErrorResponseException e) {
            response = e.getResponse();
        }
        verifyNotFoundResponse(response);
    }

    @Test
    public void deleteTest() {
        T savedEntity = client.save(entity1);
        client.delete(savedEntity.getId());

        List<T> getResult = client.get();

        assertFalse(getResult.contains(savedEntity));
    }

    @Test
    public void deleteNotExistingTest() {
        ApiErrorResponse response = null;
        try {
            client.delete(NOT_EXISTING_ID);
        } catch (ApiErrorResponseException e) {
            response = e.getResponse();
        }
        verifyNotFoundResponse(response);
    }

    @Test
    public void deleteAllTest() {
        T savedEntity1 = client.save(entity1);
        T savedEntity2 = client.save(entity2);

        client.delete();

        List<T> result = client.get();
        assertFalse(result.contains(savedEntity1));
        assertFalse(result.contains(savedEntity2));
    }

    private void verifyNotFoundResponse(ApiErrorResponse response) {
        assertEquals(NOT_FOUND_EXCEPTION_STATUS, response.getStatus());
        assertEquals(String.format(NOT_FOUND_EXCEPTION_CLASS,
                entityType.getSimpleName().replace(DTO, EMPTY_STRING)),
                response.getException());
        assertEquals(String.format(NOT_FOUND_EXCEPTION_MESSAGE,
                entityType.getSimpleName().replace(DTO, EMPTY_STRING), NOT_EXISTING_ID),
                response.getMessage());
        assertTrue(System.currentTimeMillis() - response.getTimestamp() <= SECOND);
    }

    private void verifyDuplicateResponse(ApiErrorResponse response, T entity) {
        assertEquals(ALREADY_EXIST_EXCEPTION_STATUS, response.getStatus());
        assertEquals(String.format(ALREADY_EXIST_EXCEPTION_CLASS,
                entityType.getSimpleName().replace(DTO, EMPTY_STRING)),
                response.getException());
        assertEquals(String.format(ALREADY_EXIST_EXCEPTION_MESSAGE,
                entityType.getSimpleName().replace(DTO, EMPTY_STRING),
                entity.getId()),
                response.getMessage());
        assertTrue(System.currentTimeMillis() - response.getTimestamp() <= SECOND);
    }
}
