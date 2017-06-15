package com.readit.service;

import com.readit.entity.Author;
import com.readit.entity.Book;

import java.util.List;

public interface SearchService {

    List<Book> findBooksByTitle(String title);

    List<Book> findBooksByTag(String tag);

    List<Author> findAuthorsByName(String name);
}
