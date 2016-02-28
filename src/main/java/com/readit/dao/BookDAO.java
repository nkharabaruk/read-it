package com.readit.dao;

import com.readit.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> list();

    Book get(Long id);

    List<Book> getByAuthor(Long authorId);

    List<Book> getFromCategory(Long categoryId);

    void saveOrUpdate(Book book);

    void delete(Long id);
}
