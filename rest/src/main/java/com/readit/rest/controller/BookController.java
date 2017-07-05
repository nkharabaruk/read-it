package com.readit.rest.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.BookService;
import com.readit.service.exception.BookAlreadyExistsException;
import com.readit.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/page")
    public Page<Book> getBooksPage(@RequestParam(value = "page", required = false) Integer pageNumber,
                                   @RequestParam(value = "size", required = false) Integer pageSize) {
        return bookService.findPage(new PageRequest(pageNumber, pageSize));
    }

    @GetMapping("/{bookId}")
    public Book getBookById(@PathVariable long bookId) throws BookNotFoundException {
        return bookService.findById(bookId);
    }

    @GetMapping("/{bookId}/authors")
    public List<Author> getAuthorsOfBook(@PathVariable long bookId) throws BookNotFoundException {
        return bookService.findById(bookId).getAuthors();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book) throws BookAlreadyExistsException {
        return bookService.save(book);
    }

    @DeleteMapping("/all")
    public void deleteAllBooks() {
        bookService.deleteAll();
    }

    @DeleteMapping
    public void deleteBook(@RequestBody Book book) throws BookNotFoundException {
        bookService.delete(book);
    }
}
