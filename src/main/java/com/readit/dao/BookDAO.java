package com.readit.dao;

import com.readit.entity.Book;

import java.util.List;

public interface BookDAO {
    public List<Book> list();

    public Book get(long id);

    public void saveOrUpdate(Book book);

    public void delete(long id);
}
