package com.readit.service.impl;

import com.readit.entity.Book;
import com.readit.repository.BookRepository;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CategoryService categoryService;

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Collection<Book> saveAll(Collection<Book> books) {
        return bookRepository.save(books);
    }

    public Book getById(Long id) {
        return bookRepository.findOne(id);
    }

}
