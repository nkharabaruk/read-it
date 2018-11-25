package com.readit.service.impl;

import com.readit.entity.Book;
import com.readit.repository.BookRepository;
import com.readit.service.BookService;
import com.readit.service.exception.BookAlreadyExistsException;
import com.readit.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
    }

    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.saveAll(books);
    }

    @Override
    public Book save(Book book) {
        List<Book> existing = bookRepository.findByTitleAndYearOfRelease(book.getTitle(), book.getYearOfRelease());
        if (!existing.isEmpty() && existing.contains(book)) {
            throw new BookAlreadyExistsException(existing.get(0));
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public Book update(long id, Book book) {
        try {
            Book existing = bookRepository.getOne(id);
            book.setId(existing.getId());
            return bookRepository.save(book);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new BookNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            bookRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BookNotFoundException(id);
        }
    }
}
