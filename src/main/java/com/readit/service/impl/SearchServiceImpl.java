package com.readit.service.impl;

import com.readit.dao.AuthorDAO;
import com.readit.dao.BookDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    AuthorDAO authorDAO;

    public List<Book> searchBooksByTitle(String title) {
        return bookDAO.getByTitle(title);
    }

    public List<Book> searchBooksByTag(String tag) {
        return bookDAO.getByTag(tag);
    }

    public List<Author> searchAuthorsByName(String name) {
        return authorDAO.getByName(name);
    }
}
