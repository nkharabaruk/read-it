package com.readit.controller;

import com.readit.WebApplication;
import com.readit.entity.Settings;
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
public class SettingsControllerTest {

    @Value("${local.server.port}")
    private int port;

    private final String URL = "/settingss";

    private Settings settings;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.defaultParser = Parser.JSON;

        settings = new Settings();

        // assume that db is empty
        settings.setId(1L);
        settings.setNotificationAboutNewBook(true);
        settings.setNotificationAboutNewComment(false);
    }

    @Test
    public void getAllTest() throws Exception {
        // try to get settingss when they don`t exist
        List<Settings> firstGetResult = getSettingss();
        assertFalse(firstGetResult.contains(settings));

        Settings settings1 = saveSettings();
        Settings settings2 = saveSettings();

        List<Settings> secondGetResult = getSettingss();
        assertTrue(secondGetResult.contains(settings1));
        assertTrue(secondGetResult.contains(settings2));

        deleteSettingss(settings1, settings2);
    }

    @Test
    public void getByIdTest() throws Exception {
        Settings saveResult = saveSettings();

        Settings getResult = getSettings(saveResult);
        assertEquals(saveResult, getResult);

        deleteSettings(saveResult);
    }

    @Test
    public void saveTest() throws Exception {
        Settings saveResult = saveSettings();
        assertEquals(settings, saveResult);

        deleteSettings(saveResult);
    }

    @Test
    public void deleteAllTest() throws Exception {
        Settings settings1 = saveSettings();
        Settings settings2 = saveSettings();

        List<Settings> firstGetResult = Arrays.asList(getSettings(settings1), getSettings(settings2));
        assertTrue(firstGetResult.contains(settings1));
        assertTrue(firstGetResult.contains(settings2));

        deleteSettingss(settings1, settings2);

        List<Settings> secondGetResult = getSettingss();
        assertFalse(secondGetResult.contains(settings1));
        assertFalse(secondGetResult.contains(settings2));
    }

    @Test
    public void deleteTest() throws Exception {
        Settings saveResult = saveSettings();
        assertEquals(settings, saveResult);

        deleteSettings(saveResult);

        List<Settings> getResult = getSettingss();
        assertFalse(getResult.contains(saveResult));
    }

    private List<Settings> getSettingss() {
        return Arrays.asList(when().get(URL).then()
                .statusCode(200).and().extract().as(Settings[].class));
    }

    private Settings getSettings(Settings settings) {
        return when().get(URL + "/" + settings.getId()).then()
                .statusCode(200).and().extract().as(Settings.class);
    }

    private Settings saveSettings() {
        return given().contentType(ContentType.JSON).body(settings)
                .when().post(URL).then()
                .statusCode(200).and().extract().as(Settings.class);
    }

    private void deleteSettingss(Settings...settingss) {
        given().contentType(ContentType.JSON).body(Arrays.asList(settingss))
                .when().delete(URL + "/all").then()
                .statusCode(200);
    }

    private void deleteSettings(Settings settings) {
        given().contentType(ContentType.JSON).body(settings)
                .when().delete(URL).then()
                .statusCode(200);
    }
}