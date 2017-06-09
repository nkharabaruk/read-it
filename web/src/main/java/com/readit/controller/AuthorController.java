package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.AuthorService;
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
        return  authorService.findAll();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable long authorId) {
        return authorService.findById(authorId);
    }

    @GetMapping("/{authorId}/books")
    public List<Book> getBooksOfAuthor(@PathVariable long authorId) {
        return authorService.findById(authorId).getBooks();
    }

    @PostMapping
    public List<Author> saveAuthors(@RequestBody List<Author> authors) {
        return authorService.saveAll(authors);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @DeleteMapping
    public void deleteAllAuthors() {
        authorService.deleteAll();
    }

    @DeleteMapping
    public void deleteAuthor(@RequestBody Author author) {
        authorService.delete(author);
    }
}
