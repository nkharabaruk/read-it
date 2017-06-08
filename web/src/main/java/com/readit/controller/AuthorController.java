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
        return  authorService.getAll();
    }

    @GetMapping("/{authorId}")
    public Author getAuthorById(@PathVariable Long authorId) {
        return authorService.getById(authorId);
    }

    @PostMapping
    public Author saveAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @GetMapping("/{authorId}/books")
    public List<Book> getBooksOfAuthor(@PathVariable Long authorId) {
        return authorService.getById(authorId).getBooks();
    }
}
