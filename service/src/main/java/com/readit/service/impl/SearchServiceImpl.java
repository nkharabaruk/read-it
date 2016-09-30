package com.readit.service.impl;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.repository.AuthorRepository;
import com.readit.repository.BookRepository;
import com.readit.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    public List<Book> searchBooksByTitle(String title) {
//        return bookRepository.getByTitle(title);
        return null;
    }

    public List<Book> searchBooksByTag(String tag) {
//        return bookRepository.getByTag(tag);
        return null;
    }

    public List<Author> searchAuthorsByName(String name) {
//        return authorRepository.getByName(name);
        return null;
    }
}
