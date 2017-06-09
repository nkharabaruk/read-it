package com.readit.service;

import com.readit.entity.Author;
import com.readit.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SearchService {

    List<Book> findBooksByTitle(String title);

    List<Book> findBooksByTag(String tag);

    List<Author> findAuthorsByName(String name);
}
