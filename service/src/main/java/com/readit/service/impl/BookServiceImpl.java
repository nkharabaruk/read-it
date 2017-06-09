package com.readit.service.impl;

import com.readit.entity.Book;
import com.readit.repository.BookRepository;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.save(books);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }
}
