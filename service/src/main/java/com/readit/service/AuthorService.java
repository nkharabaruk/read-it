package com.readit.service;

import com.readit.entity.Author;

import java.util.Collection;
import java.util.List;

public interface AuthorService {

    Collection<Author> getAll();

    Author getById(Long id);
}
