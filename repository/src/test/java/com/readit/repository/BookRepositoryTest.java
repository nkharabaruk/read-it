package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RepositoryApplication.class)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    private Book kobzar;
    private Book farbovanyiLys;
    private List<Book> books = new ArrayList<>();

    @Before
    public void setUp() {
        kobzar = new Book();
        kobzar.setTitle("Кобзар");
        kobzar.setDescription("Шевченків Кобзар");
        kobzar.setYearOfRelease(Year.of(1840));

        farbovanyiLys = new Book();
        farbovanyiLys.setTitle("Лис Микита");
        farbovanyiLys.setYearOfRelease(Year.of(1890));

        books.add(kobzar);
        books.add(farbovanyiLys);
    }

    @Test
    public void saveTest() {
        bookRepository.save(kobzar);
        assertEquals(kobzar, bookRepository.findOne(kobzar.getId()));
    }

    @Test
    public void saveIterableTest() {
        bookRepository.save(books);
        assertTrue(bookRepository.findAll().containsAll(books));
    }

    @Test
    public void existsTest() {
        bookRepository.save(kobzar);
        assertTrue(bookRepository.exists(kobzar.getId()));
    }

    @Test
    public void findOneTest() {
        bookRepository.save(kobzar);
        assertEquals(kobzar, bookRepository.findOne(kobzar.getId()));
    }

    @Test
    public void findAllTest() {
        bookRepository.save(books);
        assertTrue(bookRepository.findAll().containsAll(books));
    }

    @Test
    public void findAllByIdsTest() {
        bookRepository.save(books);
        List<Long> ids = books.stream().map(Book::getId).collect(Collectors.toList());
        List<Book> foundBooks = bookRepository.findAll(ids);
        assertEquals(books, foundBooks);

    }

    @Test
    public void countTest() {
        long before = bookRepository.count();
        bookRepository.save(books);
        assertEquals(before + books.size(), bookRepository.count());
    }

    @Test
    public void deleteTest() {
        bookRepository.save(kobzar);
        assertTrue(bookRepository.exists(kobzar.getId()));
        bookRepository.delete(kobzar);
        assertFalse(bookRepository.exists(kobzar.getId()));
    }

    @Test
    public void deleteByIdTest() {
        bookRepository.save(kobzar);
        assertTrue(bookRepository.exists(kobzar.getId()));
        bookRepository.delete(kobzar.getId());
        assertFalse(bookRepository.exists(kobzar.getId()));
    }

    @Test
    public void deleteIterableTest() {
        bookRepository.save(books);
        for (Book book : books) {
            assertTrue(bookRepository.exists(book.getId()));
        }
        bookRepository.delete(books);
        for (Book book : books) {
            assertFalse(bookRepository.exists(book.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        bookRepository.save(books);
        assertNotEquals(0, bookRepository.count());
        bookRepository.deleteAll();
        assertEquals(0, bookRepository.count());
    }
}