package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.AuthorService;
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
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Resource<Author>>> getAllAuthors() {
        return new ResponseEntity<>(
                authorService.getAll().stream()
                        .map(author -> new Resource<>(author, linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel()))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Author>> getAuthorById(@PathVariable Long authorId) {
        return new ResponseEntity<>(
                new Resource<>(
                        authorService.getById(authorId),
                        linkTo(methodOn(AuthorController.class).getAuthorById(authorId)).withSelfRel()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Author>> saveAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(
                new Resource<>(
                        authorService.save(author),
                        linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel()),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/{authorId}/books", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Resource<Book>>> getBooksOfAuthor(@PathVariable Long authorId) {
        return new ResponseEntity<>(
                authorService.getById(authorId).getBooks().stream()
                        .map(book -> new Resource<>(book, linkTo(methodOn(BookController.class).getBookById(book.getId())).withSelfRel()))
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
