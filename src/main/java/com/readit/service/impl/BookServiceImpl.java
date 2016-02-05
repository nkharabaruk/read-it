package com.readit.service.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Book;
import com.readit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    public List<Book> getAll() {
        return bookDAO.list();
    }

    public Book getById(Long id) {
        return bookDAO.get(id);
    }
}
