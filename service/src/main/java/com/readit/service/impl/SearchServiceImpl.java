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

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public SearchServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<Book> findBooksByTitle(String title) {
//        return bookRepository.getByTitle(title);
        return null;
    }

    public List<Book> findBooksByTag(String tag) {
//        return bookRepository.getByTag(tag);
        return null;
    }

    public List<Author> findAuthorsByName(String name) {
//        return authorRepository.getByName(name);
        return null;
    }
}
