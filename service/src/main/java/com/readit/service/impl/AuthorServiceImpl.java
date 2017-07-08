package com.readit.service.impl;

import com.readit.entity.Author;
import com.readit.repository.AuthorRepository;
import com.readit.service.AuthorService;
import com.readit.service.exception.AuthorAlreadyExistsException;
import com.readit.service.exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
        Author author = authorRepository.findOne(id);
        if (author == null) throw new AuthorNotFoundException(id);
        return author;

    }

    @Override
    public List<Author> saveAll(List<Author> list) {
        return authorRepository.save(list);
    }

    public Author save(Author author) {
        List<Author> existing = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (!existing.isEmpty() && existing.contains(author)) {
            throw new AuthorAlreadyExistsException(author);
        } else {
            return authorRepository.save(author);
        }
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            authorRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundException(id);
        }
    }
}
