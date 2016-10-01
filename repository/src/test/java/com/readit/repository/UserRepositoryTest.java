package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.File;
import com.readit.entity.Profile;
import com.readit.entity.User;
import com.readit.entity.enums.FileType;
import com.readit.entity.enums.Gender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private FileRepository fileRepository;

    private User user1;
    private User user2;
    private List<User> users = new ArrayList<>();

    @Before
    public void setUp() {
        Profile profile1 = new Profile();
        Profile profile2 = new Profile();
        profileRepository.save(profile1);
        profileRepository.save(profile2);

        File file1 = new File();
        file1.setPath("path/to/file1");
        file1.setType(FileType.OTHER);
        File file2 = new File();
        file2.setPath("path/to/file2");
        file2.setType(FileType.OTHER);
        fileRepository.save(file1);
        fileRepository.save(file2);

        user1 = new User();
        user1.setFirstName("user1 first name");
        user1.setLastName("user1 last name");
        user1.setGender(Gender.MALE);
        user1.setDateOfBirth(LocalDate.now());
        user1.setPassword("user1 password");
        user1.setProfile(profile1);


        user2 = new User();
        user2.setFirstName("user2 first name");
        user2.setLastName("user2 last name");
        user2.setGender(Gender.FEMALE);
        user2.setDateOfBirth(LocalDate.now());
        user2.setPassword("user2 password");
        user2.setProfile(profile2);

        users.add(user1);
        users.add(user2);
    }

    @Test
    public void saveTest() {
        userRepository.save(user1);
        assertEquals(user1, userRepository.findOne(user1.getId()));
    }

    @Test
    public void saveIterableTest() {
        userRepository.save(users);
        assertTrue(userRepository.findAll().containsAll(users));
    }

    @Test
    public void existsTest() {
        userRepository.save(user1);
        assertTrue(userRepository.exists(user1.getId()));
    }

    @Test
    public void findOneTest() {
        userRepository.save(user1);
        assertEquals(user1, userRepository.findOne(user1.getId()));
    }

    @Test
    public void findAllTest() {
        userRepository.save(users);
        assertTrue(userRepository.findAll().containsAll(users));
    }

    @Test
    public void findAllByIdsTest() {
        userRepository.save(users);
        List<Long> ids = users.stream().map(User::getId).collect(Collectors.toList());
        List<User> foundUsers = userRepository.findAll(ids);
        assertEquals(users, foundUsers);

    }

    @Test
    public void countTest() {
        long before = userRepository.count();
        userRepository.save(users);
        assertEquals(before + users.size(), userRepository.count());
    }

    @Test
    public void deleteTest() {
        userRepository.save(user1);
        assertTrue(userRepository.exists(user1.getId()));
        userRepository.delete(user1);
        assertFalse(userRepository.exists(user1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        userRepository.save(user1);
        assertTrue(userRepository.exists(user1.getId()));
        userRepository.delete(user1.getId());
        assertFalse(userRepository.exists(user1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        userRepository.save(users);
        users.forEach(User -> assertTrue(userRepository.exists(User.getId())));
        userRepository.delete(users);
        users.forEach(User -> assertFalse(userRepository.exists(User.getId())));
    }

    @Test
    public void deleteAllTest() {
        userRepository.save(users);
        assertNotEquals(0, userRepository.count());
        userRepository.deleteAll();
        assertEquals(0, userRepository.count());
    }

}