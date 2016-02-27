package com.readit.controller;

import com.readit.entity.Book;
import com.readit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/getAllBooks")
    List<Book> getAllBooks() {
        List<Book> books = bookService.getAll();
        return books;
    }

    @RequestMapping("/b/{id}")
    public Book getBook(@PathVariable long id) {
        return bookService.getById(id);
    }
}
