package com.readit.repository;

import com.readit.RepositoryApplication;
import com.readit.entity.Book;
import com.readit.entity.File;
import com.readit.entity.Quote;
import com.readit.entity.Tag;
import com.readit.entity.enums.FileType;
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
public class QuoteRepositoryTest {

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private FileRepository fileRepository;

    private Quote quote1;
    private Quote quote2;
    private List<Quote> quotes = new ArrayList<>();

    @Before
    public void setUp() {
        Book book1 = new Book();
        book1.setTitle("Book with tag1");
        Book book2 = new Book();
        book2.setTitle("Book with tag2");
        bookRepository.save(book1);
        bookRepository.save(book2);

        File file1 = new File();
        file1.setPath("path/to/file1");
        file1.setType(FileType.OTHER);
        File file2 = new File();
        file2.setPath("path/to/file2");
        file2.setType(FileType.OTHER);
        fileRepository.save(file1);
        fileRepository.save(file2);

        quote1 = new Quote();
        quote1.setText("Tag 1");
        quote1.setBook(book1);
        quote1.setBackground(file1);

        quote2 = new Quote();
        quote2.setText("Tag 2");
        quote2.setBook(book2);
        quote2.setBackground(file2);

        quotes.add(quote1);
        quotes.add(quote2);
    }

    @Test
    public void saveTest() {
        quoteRepository.save(quote1);
        assertEquals(quote1, quoteRepository.findOne(quote1.getId()));
    }

    @Test
    public void saveIterableTest() {
        quoteRepository.save(quotes);
        assertTrue(quoteRepository.findAll().containsAll(quotes));
    }

    @Test
    public void existsTest() {
        quoteRepository.save(quote1);
        assertTrue(quoteRepository.exists(quote1.getId()));
    }

    @Test
    public void findOneTest() {
        quoteRepository.save(quote1);
        assertEquals(quote1, quoteRepository.findOne(quote1.getId()));
    }

    @Test
    public void findAllTest() {
        quoteRepository.save(quotes);
        assertTrue(quoteRepository.findAll().containsAll(quotes));
    }

    @Test
    public void findAllByIdsTest() {
        quoteRepository.save(quotes);
        List<Long> ids = quotes.stream().map(Quote::getId).collect(Collectors.toList());
        List<Quote> foundQuotes = quoteRepository.findAll(ids);
        assertEquals(quotes, foundQuotes);

    }

    @Test
    public void countTest() {
        long before = quoteRepository.count();
        quoteRepository.save(quotes);
        assertEquals(before + quotes.size(), quoteRepository.count());
    }

    @Test
    public void deleteTest() {
        quoteRepository.save(quote1);
        assertTrue(quoteRepository.exists(quote1.getId()));
        quoteRepository.delete(quote1);
        assertFalse(quoteRepository.exists(quote1.getId()));
    }

    @Test
    public void deleteByIdTest() {
        quoteRepository.save(quote1);
        assertTrue(quoteRepository.exists(quote1.getId()));
        quoteRepository.delete(quote1.getId());
        assertFalse(quoteRepository.exists(quote1.getId()));
    }

    @Test
    public void deleteIterableTest() {
        quoteRepository.save(quotes);
        quotes.forEach(quote -> assertTrue(quoteRepository.exists(quote.getId())));
        quoteRepository.delete(quotes);
        quotes.forEach(quote -> assertFalse(quoteRepository.exists(quote.getId())));
    }

    @Test
    public void deleteAllTest() {
        quoteRepository.save(quotes);
        assertNotEquals(0, quoteRepository.count());
        quoteRepository.deleteAll();
        assertEquals(0, quoteRepository.count());
    }

}