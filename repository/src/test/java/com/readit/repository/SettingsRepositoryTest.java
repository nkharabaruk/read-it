package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Settings;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryApplication.class)
public class SettingsRepositoryTest {

    @Autowired
    private SettingsRepository settingsRepository;

    private Settings settings1;
    private Settings settings2;
    private List<Settings> settings = new ArrayList<>();

    @Before
    public void setUp() {
        settings1 = new Settings();
        settings1.setNotificationAboutNewBook(true);
        settings1.setNotificationAboutNewCommentar(false);

        settings2 = new Settings();
        settings2.setNotificationAboutNewBook(false);
        settings2.setNotificationAboutNewCommentar(true);

        settings.add(settings1);
        settings.add(settings2);
    }

    @Test
    public void saveTest() {
        settingsRepository.save(settings1);
        assertEquals(settings1, settingsRepository.findOne(settings1.getId()));
    }

    @Test
    public void saveIterableTest() {
        settingsRepository.save(settings);
        assertTrue(settingsRepository.findAll().containsAll(settings));
    }

    @Test
    public void existsTest() {
        settingsRepository.save(settings1);
        assertTrue(settingsRepository.exists(settings1.getId()));
    }

    @Test
    public void findOneTest() {
        settingsRepository.save(settings1);
        assertEquals(settings1, settingsRepository.findOne(settings1.getId()));
    }

    @Test
    public void findAllTest() {
        settingsRepository.save(settings);
        assertTrue(settingsRepository.findAll().containsAll(settings));
    }

    @Test
    public void findAllByIdsTest() {
        settingsRepository.save(settings);
        List<Long> ids = settings.stream().map(Settings::getId).collect(Collectors.toList());
        List<Settings> foundSettings = settingsRepository.findAll(ids);
        assertEquals(settings, foundSettings);

    }

    @Test
    public void countTest() {
        long before = settingsRepository.count();
        settingsRepository.save(settings);
        assertEquals(before + settings.size(), settingsRepository.count());
    }

    @Test
    public void deleteTest() {
        settingsRepository.save(settings1);
        assertTrue(settingsRepository.exists(settings1.getId()));
        settingsRepository.delete(settings1);
        assertFalse(settingsRepository.exists(settings1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        settingsRepository.save(settings1);
        assertTrue(settingsRepository.exists(settings1.getId()));
        settingsRepository.delete(settings1.getId());
        assertFalse(settingsRepository.exists(settings1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        settingsRepository.save(settings);
        settings.forEach(settings -> assertTrue(settingsRepository.exists(settings.getId())));
        settingsRepository.delete(settings);
        settings.forEach(settings -> assertFalse(settingsRepository.exists(settings.getId())));
    }

    @Test
    public void deleteAllTest() {
        settingsRepository.save(settings);
        assertNotEquals(0, settingsRepository.count());
        settingsRepository.deleteAll();
        assertEquals(0, settingsRepository.count());
    }

}