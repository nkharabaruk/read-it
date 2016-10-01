package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Comment;
import com.readit.entity.User;
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
import java.time.Month;
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
public class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    private Comment good;
    private Comment bad;

    private List<Comment> comments = new ArrayList<>();

    private User user1;
    private User user2;

    @Before
    public void setUp() {

        user1 = new User();
        user1.setFirstName("Ivan");
        user1.setLastName("Ivanov");
        user1.setGender(Gender.MALE);
        user1.setDateOfBirth(LocalDate.of(1991, Month.DECEMBER, 1));

        user2 = new User();
        user2.setFirstName("Marta");
        user2.setLastName("Zdan");
        user2.setGender(Gender.FEMALE);
        user2.setDateOfBirth(LocalDate.of(1995, Month.JUNE, 26));

        good = new Comment();
        good.setTheme("Such a beautiful book!");
        good.setAuthor(user1);
        good.setText("I`m so excited of this book! It`s my favourite.");

        bad = new Comment();
        bad.setTheme("This book is boring.");
        bad.setAuthor(user2);
        bad.setText("I tried to read the book, but I became asleep every time. " +
                "It`s boring. I haven`t nothing to add.");

        comments.add(good);
        comments.add(bad);
    }

    @Test
    public void saveTest() {
        commentRepository.save(good);
        assertEquals(good, commentRepository.findOne(good.getId()));
    }

    @Test
    public void saveIterableTest() {
        commentRepository.save(comments);
        assertTrue(commentRepository.findAll().containsAll(comments));
    }

    @Test
    public void existsTest() {
        commentRepository.save(good);
        assertTrue(commentRepository.exists(good.getId()));
    }

    @Test
    public void findOneTest() {
        commentRepository.save(good);
        assertEquals(good, commentRepository.findOne(good.getId()));
    }

    @Test
    public void findAllTest() {
        commentRepository.save(comments);
        assertTrue(commentRepository.findAll().containsAll(comments));
    }

    @Test
    public void findAllByIdsTest() {
        commentRepository.save(comments);
        List<Long> ids = comments.stream().map(Comment::getId).collect(Collectors.toList());
        List<Comment> foundComments = commentRepository.findAll(ids);
        assertEquals(comments, foundComments);

    }

    @Test
    public void countTest() {
        long before = commentRepository.count();
        commentRepository.save(comments);
        assertEquals(before + comments.size(), commentRepository.count());
    }

    @Test
    public void deleteTest() {
        commentRepository.save(good);
        assertTrue(commentRepository.exists(good.getId()));
        commentRepository.delete(good);
        assertFalse(commentRepository.exists(good.getId()));
    }

    @Test
    public void deleteByIdTest() {
        commentRepository.save(good);
        assertTrue(commentRepository.exists(good.getId()));
        commentRepository.delete(good.getId());
        assertFalse(commentRepository.exists(good.getId()));
    }

    @Test
    public void deleteIterableTest() {
        commentRepository.save(comments);
        for (Comment comment : comments) {
            assertTrue(commentRepository.exists(comment.getId()));
        }
        commentRepository.delete(comments);
        for (Comment comment : comments) {
            assertFalse(commentRepository.exists(comment.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        commentRepository.save(comments);
        assertNotEquals(0, commentRepository.count());
        commentRepository.deleteAll();
        assertEquals(0, commentRepository.count());
    }
}
