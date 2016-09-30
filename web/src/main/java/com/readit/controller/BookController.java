package com.readit.controller;

import com.readit.entity.Book;
import com.readit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Collection;

@EnableWebMvc
@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping("/getAllBooks")
    Collection<Book> getAllBooks() {
        return bookService.getAll();
    }

    @RequestMapping("/getBookById/{bookId}")
    public Book getBookById(@PathVariable Long bookId) {
        return bookService.getById(bookId);
    }

    @RequestMapping("/getBooksByAuthor/{authorId}")
    public Collection<Book> getBooksByAuthor(@PathVariable Long authorId) {
//        return bookService.getByAuthor(authorId);
        return null;
    }

    @RequestMapping("/getBooksFromCategory/{categoryId}")
    public Collection<Book> getBooksFromCategory(@PathVariable Long categoryId) {
//        return bookService.getFromCategory(categoryId);
        return null;
    }

    @RequestMapping("/getBooksFromCategoryAndDescendants/{categoryId}")
    public Collection<Book> getBooksFromCategoryAndDescendants(@PathVariable Long categoryId) {
//        return bookService.getFromCategoryAndDescendants(categoryId);
        return null;
    }
}
