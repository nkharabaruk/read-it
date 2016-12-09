package com.readit.service.impl;

import com.readit.entity.Author;
import com.readit.repository.AuthorRepository;

import com.readit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public Collection<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findOne(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
