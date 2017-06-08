package com.readit.service.impl;

import com.readit.entity.Author;
import com.readit.repository.AuthorRepository;

import com.readit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }

    public Author getById(Long id) {
        return authorRepository.findOne(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
