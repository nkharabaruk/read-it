package com.readit.controller;

import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/searchBooksByTitle/{title}")
    public List<Book> searchBooksByTitle(@PathVariable String title) {
        return searchService.findBooksByTitle(title);
    }

    @GetMapping("/searchBooksByTag/{tag}")
    public List<Book> searchBooksByTag(@PathVariable String tag) {
        return searchService.findBooksByTag(tag);
    }

    @GetMapping("/searchAuthorsByName/{name}")
    public List<Author> searchAuthorsByName(@PathVariable String name) {
        return searchService.findAuthorsByName(name);
    }
}
