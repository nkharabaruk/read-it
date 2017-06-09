package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Profile;
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
public class ProfileControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/profiles";

    private Profile profile;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        profile = new Profile();

        // assume that db is empty
        profile.setId(1L);
        profile.setSettings(null);

        // initialized to prevent null-[] comparing problems
        profile.setWasRead(new ArrayList<>());
        profile.setIsReading(new ArrayList<>());
        profile.setWantToRead(new ArrayList<>());
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get profiles when they don`t exist
        List<Profile> firstGetResult = getProfiles();
        assertFalse(firstGetResult.contains(profile));

        Profile profile1 = saveProfile();
        Profile profile2 = saveProfile();

        List<Profile> secondGetResult = getProfiles();
        assertTrue(secondGetResult.contains(profile1));
        assertTrue(secondGetResult.contains(profile2));

        deleteProfiles(profile1, profile2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Profile saveResult = saveProfile();

        Profile getResult = getProfile(saveResult);
        assertEquals(saveResult, getResult);

        deleteProfile(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Profile saveResult = saveProfile();
        assertEquals(profile, saveResult);

        deleteProfile(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Profile profile1 = saveProfile();
        Profile profile2 = saveProfile();

        List<Profile> firstGetResult = Arrays.asList(getProfile(profile1), getProfile(profile2));
        assertTrue(firstGetResult.contains(profile1));
        assertTrue(firstGetResult.contains(profile2));

        deleteProfiles(profile1, profile2);

        List<Profile> secondGetResult = getProfiles();
        assertFalse(secondGetResult.contains(profile1));
        assertFalse(secondGetResult.contains(profile2));
    }

    @Test
    public void deleteTest() throws Exception {
        Profile saveResult = saveProfile();
        assertEquals(profile, saveResult);

        deleteProfile(saveResult);

        List<Profile> getResult = getProfiles();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Profile> getProfiles() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Profile[].class));
    }

    private Profile getProfile(Profile profile) {
        return when().get(URL + "/" + profile.getId()).then()
                .statusCode(200).and().extract().as(Profile.class);
    }

    private Profile saveProfile() {
        return given().contentType(ContentType.JSON).body(profile)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Profile.class);
    }

    private void deleteProfiles(Profile...profiles) {
        given().contentType(ContentType.JSON).body(Arrays.asList(profiles))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteProfile(Profile profile) {
        given().contentType(ContentType.JSON).body(profile)
                .when().delete(URL).then()
                .statusCode(200);
    }
}