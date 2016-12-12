package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Resource<Book>>> getAllBooks() {
        return new ResponseEntity<>(
                bookService.getAll().stream()
                        .map(book -> new Resource<>(book, linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel()))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookId}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Book>> getBookById(@PathVariable Long bookId) {
        return new ResponseEntity<>(
                new Resource<>(
                        bookService.getById(bookId),
                        linkTo(methodOn(BookController.class).getBookById(bookId)).withSelfRel()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Book>> saveBook(@RequestBody Book book) {
        return new ResponseEntity<>(
                new Resource<>(
                        bookService.save(book),
                        linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{bookId}/authors", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Resource<Author>>> getAuthorsOfBook(@PathVariable Long bookId) {
        return new ResponseEntity<>(
                bookService.getById(bookId).getAuthors().stream()
                        .map(author -> new Resource<>(author, linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel()))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
