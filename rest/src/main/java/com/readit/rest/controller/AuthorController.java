package com.readit.rest.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.AuthorService;
import com.readit.service.exception.AuthorAlreadyExistsException;
import com.readit.service.exception.AuthorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable long authorId) throws AuthorNotFoundException {
        return authorService.findById(authorId);
    }

    @GetMapping("/{authorId}/books")
    public List<Book> getBooksOfAuthor(@PathVariable long authorId) throws AuthorNotFoundException {
        return authorService.findById(authorId).getBooks();
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) throws AuthorAlreadyExistsException {
        return authorService.save(author);
    }

    @DeleteMapping
    public void deleteAllAuthors() {
        authorService.deleteAll();
    }

    @DeleteMapping("/{authorId}")
    public void deleteAuthor(@PathVariable long authorId) throws AuthorNotFoundException {
        authorService.delete(authorId);
    }
}
