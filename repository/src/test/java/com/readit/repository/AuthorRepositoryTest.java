package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Author;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@Rollback
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryApplication.class)
public class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository authorRepository;

    private Author shevchenko;
    private Author franko;
    private List<Author> authors = new ArrayList<>();

    @Before
    public void setUp() {
        shevchenko = new Author();
        shevchenko.setFirstName("Тарас");
        shevchenko.setMiddleName("Григорович");
        shevchenko.setLastName("Шевченко");
        shevchenko.setDateOfBirth(LocalDate.of(1814, Month.MARCH, 9));
        shevchenko.setDateOfDeath(LocalDate.of(1861, Month.MARCH, 10));
        shevchenko.setBiography("Народився в Моринцях");

        franko = new Author();
        franko.setFirstName("Іван");
        franko.setMiddleName("Якович");
        franko.setLastName("Франко");
        franko.setDateOfBirth(LocalDate.of(1856, 8, 27));
        franko.setDateOfDeath(LocalDate.of(1916, 5, 28));
        franko.setBiography("Народився в Нагуєвичах");

        authors.add(shevchenko);
        authors.add(franko);
    }

    @Test
    public void saveTest() {
        authorRepository.save(shevchenko);
        assertEquals(shevchenko, authorRepository.findOne(shevchenko.getId()));
    }

    @Test
    public void saveIterableTest() {
        authorRepository.save(authors);
        assertTrue(authorRepository.findAll().containsAll(authors));
    }

    @Test
    public void existsTest() {
        authorRepository.save(shevchenko);
        assertTrue(authorRepository.exists(shevchenko.getId()));
    }

    @Test
    public void findOneTest() {
        authorRepository.save(shevchenko);
        assertEquals(shevchenko, authorRepository.findOne(shevchenko.getId()));
    }

    @Test
    public void findAllTest() {
        authorRepository.save(authors);
        assertTrue(authorRepository.findAll().containsAll(authors));
    }

    @Test
    public void findAllByIdsTest() {
        authorRepository.save(authors);
        List<Long> ids = authors.stream().map(Author::getId).collect(Collectors.toList());
        List<Author> foundAuthors = authorRepository.findAll(ids);
        assertEquals(authors, foundAuthors);

    }

    @Test
    public void countTest() {
        long before = authorRepository.count();
        authorRepository.save(authors);
        assertEquals(before + authors.size(), authorRepository.count());
    }

    @Test
    public void deleteTest() {
        authorRepository.save(shevchenko);
        assertTrue(authorRepository.exists(shevchenko.getId()));
        authorRepository.delete(shevchenko);
        assertFalse(authorRepository.exists(shevchenko.getId()));
    }

    @Test
    public void deleteByIdTest() {
        authorRepository.save(shevchenko);
        assertTrue(authorRepository.exists(shevchenko.getId()));
        authorRepository.delete(shevchenko.getId());
        assertFalse(authorRepository.exists(shevchenko.getId()));
    }

    @Test
    public void deleteIterableTest() {
        authorRepository.save(authors);
        for (Author author : authors) {
            assertTrue(authorRepository.exists(author.getId()));
        }
        authorRepository.delete(authors);
        for (Author author : authors) {
            assertFalse(authorRepository.exists(author.getId()));
        }
    }

    @Test
    public void deleteAllTest() {
        authorRepository.save(authors);
        assertNotEquals(0, authorRepository.count());
        authorRepository.deleteAll();
        assertEquals(0, authorRepository.count());
    }

    @Test
    public void findByFirstNameContainingTest() {
        authorRepository.save(shevchenko);
        assertTrue(authorRepository.findByFirstNameContaining("Тар").contains(shevchenko));
    }

    @Test
    public void findByMiddleNameContainingTest() {
        authorRepository.save(franko);
        assertTrue(authorRepository.findByMiddleNameContaining("Як").contains(franko));
    }

    @Test
    public void findByLastNameContainingTest() {
        authorRepository.save(authors);
        assertTrue(authorRepository.findByLastNameContaining("ко").containsAll(authors));
    }
}