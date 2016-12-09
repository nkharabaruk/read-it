package com.readit.controller;

import com.readit.entity.Author;
import com.readit.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<List<Resource<Author>>> getAllAuthors() {
        List<Resource<Author>> resourceList = new ArrayList<>();
        for (Author author : authorService.getAll()) {
            resourceList.add(new Resource<>(author, linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel()));
        }
        return new ResponseEntity<>(resourceList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Author>> getAuthorById(@PathVariable Long authorId) {
        Resource<Author> resourceAuthor = new Resource<>(authorService.getById(authorId), linkTo(methodOn(AuthorController.class).getAuthorById(authorId)).withSelfRel());
        return new ResponseEntity<>(resourceAuthor, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<Resource<Author>> saveAuthor(@RequestBody Author author) {
        Resource<Author> resourceAuthor = new Resource<>(authorService.save(author), linkTo(methodOn(AuthorController.class).getAuthorById(author.getId())).withSelfRel());
        return new ResponseEntity<>(resourceAuthor, HttpStatus.OK);
    }
}
