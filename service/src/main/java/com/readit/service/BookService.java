package com.readit.service;

import com.readit.entity.Book;

import java.util.Collection;

public interface BookService {

    Book getById(Long id);

    Collection<Book> getAll();

    Book save(Book book);

    Collection<Book> saveAll(Collection<Book> books);
}
