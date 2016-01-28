package com.readit.dao.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Book;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {
    public BookDAOImpl() {
    }

    public List<Book> list() {
        return null;
    }

    public Book get(long id) {
        return null;
    }

    public void saveOrUpdate(Book book) {

    }

    public void delete(long id) {

    }
}
