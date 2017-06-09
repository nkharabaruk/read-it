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

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(long id) {
        return authorRepository.getOne(id);
    }

    @Override
    public List<Author> saveAll(List<Author> list) {
        return authorRepository.save(list);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public void delete(Author author) {
        authorRepository.delete(author);
    }
}
