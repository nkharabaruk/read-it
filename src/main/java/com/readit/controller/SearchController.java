package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @RequestMapping("/searchBooksByTitle/{title}")
    public List<Book> searchBooksByTitle(@PathVariable String title) {
        return searchService.searchBooksByTitle(title);
    }

    @RequestMapping("/searchAuthorsByName/{name}")
    public List<Author> searchAuthorsByName(@PathVariable String name) {
        return searchService.searchAuthorsByName(name);
    }
}
