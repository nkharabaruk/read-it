package com.readit.service;

import com.readit.entity.Author;
import com.readit.service.exception.AuthorAlreadyExistsException;
import com.readit.service.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(long id) throws AuthorNotFoundException;

    List<Author> saveAll(List<Author> list);

    Author save(Author author) throws AuthorAlreadyExistsException;

    void deleteAll();

    void delete(long id) throws AuthorNotFoundException;
}
