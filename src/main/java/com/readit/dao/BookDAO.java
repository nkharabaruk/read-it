package com.readit.dao;

import com.readit.entity.Author;
import com.readit.entity.Book;

import java.util.List;

public interface BookDAO {
    List<Book> list();

    Book get(Long id);
    List<Author> getAuthors(Long id);
    void saveOrUpdate(Book book);

    void delete(Long id);

}
