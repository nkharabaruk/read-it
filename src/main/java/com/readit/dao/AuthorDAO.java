package com.readit.dao;

import com.readit.entity.Author;

import java.util.List;

public interface AuthorDAO {
    List<Author> list();

    Author get(Long id);

    void saveOrUpdate(Author author);

    void delete(Long id);
}
