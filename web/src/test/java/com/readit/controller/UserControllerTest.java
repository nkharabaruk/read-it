package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.User;
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
public class UserControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/users";

    private User user;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        user = new User();

        // assume that db is empty
        user.setId(1L);
        user.setFirstName("Антоніо");
        user.setLastName("Бандерас");
        user.setGender(null);
        user.setDateOfBirth(null);
        user.setAvatar(null);
        user.setProfile(null);
        user.setPassword("1234");
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get users when they don`t exist
        List<User> firstGetResult = getUsers();
        assertFalse(firstGetResult.contains(user));

        User user1 = saveUser();
        User user2 = saveUser();

        List<User> secondGetResult = getUsers();
        assertTrue(secondGetResult.contains(user1));
        assertTrue(secondGetResult.contains(user2));

        deleteUsers(user1, user2);
    }

    @Test
    public void getByIdTest() throws Exception {
        User saveResult = saveUser();

        User getResult = getUser(saveResult);
        assertEquals(saveResult, getResult);

        deleteUser(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        User saveResult = saveUser();
        assertEquals(user, saveResult);

        deleteUser(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        User user1 = saveUser();
        User user2 = saveUser();

        List<User> firstGetResult = Arrays.asList(getUser(user1), getUser(user2));
        assertTrue(firstGetResult.contains(user1));
        assertTrue(firstGetResult.contains(user2));

        deleteUsers(user1, user2);

        List<User> secondGetResult = getUsers();
        assertFalse(secondGetResult.contains(user1));
        assertFalse(secondGetResult.contains(user2));
    }

    @Test
    public void deleteTest() throws Exception {
        User saveResult = saveUser();
        assertEquals(user, saveResult);

        deleteUser(saveResult);

        List<User> getResult = getUsers();
        assertFalse(getResult.contains(saveResult));
    }

    private List<User> getUsers() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(User[].class));
    }

    private User getUser(User user) {
        return when().get(URL + "/" + user.getId()).then()
                .statusCode(200).and().extract().as(User.class);
    }

    private User saveUser() {
        return given().contentType(ContentType.JSON).body(user)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(User.class);
    }

    private void deleteUsers(User...users) {
        given().contentType(ContentType.JSON).body(Arrays.asList(users))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteUser(User user) {
        given().contentType(ContentType.JSON).body(user)
                .when().delete(URL).then()
                .statusCode(200);
    }
}