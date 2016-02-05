package com.readit.service;

import com.readit.entity.Book;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Book getById(Long id);
}
