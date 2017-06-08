package com.readit.service;

import com.readit.entity.Author;
import com.readit.entity.Book;

import java.util.List;

public interface SearchService {

    List<Book> searchBooksByTitle(String title);

    List<Book> searchBooksByTag(String tag);

    List<Author> searchAuthorsByName(String name);
}
