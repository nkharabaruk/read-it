package com.readit.dao;

import com.readit.entity.Book;
import com.readit.entity.Tag;

import java.util.List;

public interface BookDAO {
    List<Book> list();

    Book get(Long id);

    List<Book> getByAuthor(Long authorId);

    List<Book> getFromCategory(Long categoryId);

    List<Book> getByTitle(String title);

    List<Book> getByTag(String tag);

    void saveOrUpdate(Book book);

    void delete(Long id);
}
