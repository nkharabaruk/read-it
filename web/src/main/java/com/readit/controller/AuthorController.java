package com.readit.controller;

import com.readit.controller.exception.AlreadyExistsException;
import com.readit.controller.exception.NotFoundException;
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
    public Author getAuthorById(@PathVariable long authorId) {
        try {
            return authorService.findById(authorId);
        } catch (AuthorNotFoundException e) {
            //TODO: add logger
            throw new NotFoundException("Author is not found.");
        }
    }

    @GetMapping("/{authorId}/books")
    public List<Book> getBooksOfAuthor(@PathVariable long authorId) {
        try {
            return authorService.findById(authorId).getBooks();
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException("No such author.");
        }
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        try {
            return authorService.save(author);
        } catch (AuthorAlreadyExistsException e) {
            throw new AlreadyExistsException("Author already exist.");
        }
    }

    @DeleteMapping("/all")
    public void deleteAllAuthors() {
        authorService.deleteAll();
    }

    @DeleteMapping
    public void deleteAuthor(@RequestBody Author author) {
        try {
            authorService.delete(author);
        } catch (AuthorNotFoundException e) {
            throw new NotFoundException("Cannot delete not existing author.");
        }
    }
}
