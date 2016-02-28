package com.readit.service;

import com.readit.entity.Author;
import com.readit.entity.Book;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Long id);

    List<Author> getBookAuthors(Long bookId);
}
