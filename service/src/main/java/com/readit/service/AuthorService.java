package com.readit.service;

import com.readit.entity.Author;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(Long id);

    Author save(Author author);
}
