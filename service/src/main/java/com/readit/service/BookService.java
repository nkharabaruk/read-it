package com.readit.service;

import com.readit.entity.Book;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BookService {

    Book getById(Long id);

    Collection<Book> getAll();

    Book save(Book book);

    Collection<Book> saveAll(Collection<Book> books);
}
