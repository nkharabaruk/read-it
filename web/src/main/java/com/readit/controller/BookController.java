package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/page")
    public Page<Book> getBooksByPage(@RequestParam(value = "page", required = false) Integer pageNumber,
                                     @RequestParam(value = "size", required = false) Integer pageSize) {
        return bookService.getBookPage(new PageRequest(pageNumber, pageSize));
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getById(bookId);
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/{bookId}/authors")
    public List<Author> getAuthorsOfBook(@PathVariable Long bookId) {
        return bookService.getById(bookId).getAuthors();
    }
}
