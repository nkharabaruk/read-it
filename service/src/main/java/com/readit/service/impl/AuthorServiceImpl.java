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
        return authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
    }

    @Override
    public List<Author> saveAll(List<Author> list) {
        return authorRepository.saveAll(list);
    }

    public Author save(Author author) {
        List<Author> existing = authorRepository.findByFirstNameAndLastName(author.getFirstName(), author.getLastName());
        if (!existing.isEmpty() && existing.contains(author)) {
            throw new AuthorAlreadyExistsException(existing.get(0));
        } else {
            return authorRepository.save(author);
        }
    }

    @Override
    public Author update(long id, Author author) {
        Author existing = findById(id);
        author.setId(existing.getId());
        return authorRepository.save(author);
    }

    @Override
    public void deleteAll() {
        authorRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            authorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorNotFoundException(id);
        }
    }
}
