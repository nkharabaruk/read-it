package com.readit.dao;

import com.readit.entity.Author;

import java.util.List;

public interface AuthorDAO {
    public List<Author> list();

    public Author get(long id);

    public void saveOrUpdate(Author author);

    public void delete(long id);
}
