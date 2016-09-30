package com.readit.controller;

import com.readit.entity.Author;
import com.readit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping("/getAllAuthors")
    public Collection<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @RequestMapping("/getAuthorById/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getById(authorId);
    }
}
