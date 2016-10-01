package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Book;
import com.readit.entity.Profile;
import com.readit.entity.Settings;
import com.readit.entity.User;
import com.readit.entity.enums.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by nataliia on 29.09.16.
 */

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryApplication.class)
public class ProfileRepositoryTest {

    @Autowired
    private ProfileRepository profileRepository;

    private Profile profile1;
    private Profile profile2;

    private List<Profile> profiles = new ArrayList<>();
    
    private Book book1;
    private Book book2;
    private Book book3;

    private List<Book> books = new ArrayList<>();
    
    private Settings settings;

    @Before
    public void setUp() {

        book1 = new Book();
        book1.setTitle("Every Time I go home");
        
        book2 = new Book();
        book2.setTitle("Cat and kittens");
        
        book3 = new Book();
        book3.setTitle("War and Peace");

        books.add(book1);
        books.add(book2);
        books.add(book3);

        settings = new Settings();
        settings.setNotificationAboutNewBook(true);
        settings.setNotificationAboutNewCommentar(false);

        profile1 = new Profile();
        profile1.setWantToRead(books);
        profile1.setIsReading(books);
        profile1.setWasRead(books);
        profile1.setSettings(settings);

        profile2 = new Profile();
        profile2.setWantToRead(books);
        profile2.setIsReading(books);
        profile2.setWasRead(null);
        profile2.setSettings(settings);

        profiles.add(profile1);
        profiles.add(profile2);
    }

    @Test
    public void saveTest() {
        profileRepository.save(profile1);
        assertEquals(profile1, profileRepository.findOne(profile1.getId()));
    }

    @Test
    public void saveIterableTest() {
        profileRepository.save(profiles);
        assertTrue(profileRepository.findAll().containsAll(profiles));
    }

    @Test
    public void existsTest() {
        profileRepository.save(profile1);
        assertTrue(profileRepository.exists(profile1.getId()));
    }

    @Test
    public void findOneTest() {
        profileRepository.save(profile1);
        assertEquals(profile1, profileRepository.findOne(profile1.getId()));
    }

    @Test
    public void findAllTest() {
        profileRepository.save(profiles);
        assertTrue(profileRepository.findAll().containsAll(profiles));
    }

    @Test
    public void findAllByIdsTest() {
        profileRepository.save(profiles);
        List<Long> ids = profiles.stream().map(Profile::getId).collect(Collectors.toList());
        List<Profile> foundprofiles = profileRepository.findAll(ids);
        assertEquals(profiles, foundprofiles);

    }

    @Test
    public void countTest() {
        long before = profileRepository.count();
        profileRepository.save(profiles);
        assertEquals(before + profiles.size(), profileRepository.count());
    }

    @Test
    public void deleteTest() {
        profileRepository.save(profile1);
        assertTrue(profileRepository.exists(profile1.getId()));
        profileRepository.delete(profile1);
        assertFalse(profileRepository.exists(profile1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        profileRepository.save(profile1);
        assertTrue(profileRepository.exists(profile1.getId()));
        profileRepository.delete(profile1.getId());
        assertFalse(profileRepository.exists(profile1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        profileRepository.save(profiles);
        for (Profile profile : profiles) {
            assertTrue(profileRepository.exists(profile.getId()));
        }
        profileRepository.delete(profiles);
        for (Profile profile : profiles) {
            assertFalse(profileRepository.exists(profile.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        profileRepository.save(profiles);
        assertNotEquals(0, profileRepository.count());
        profileRepository.deleteAll();
        assertEquals(0, profileRepository.count());
    }
}
