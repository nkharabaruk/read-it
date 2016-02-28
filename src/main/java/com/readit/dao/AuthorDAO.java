package com.readit.dao;

import com.readit.entity.Author;
import com.readit.entity.Book;

import java.util.List;

public interface AuthorDAO {
    List<Author> list();

    Author get(Long id);

    List<Author> getBookAuthors(Long bookId);

    void saveOrUpdate(Author author);

    void delete(Long id);
}
