package com.readit.service;

import com.readit.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(long id);

    List<Author> saveAll(List<Author> list);

    Author save(Author author);

    void deleteAll();

    void delete(Author author);
}
