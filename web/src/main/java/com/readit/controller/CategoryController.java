package com.readit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final JsonPropertyFilterMixIn jsonFilter;

    @Autowired
    public CategoryController(CategoryService categoryService, JsonPropertyFilterMixIn jsonFilter) {
        this.categoryService = categoryService;
        this.jsonFilter = jsonFilter;
    }

    @GetMapping("/getRootCategories")
    public String getRootCategories() throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findRootCategories(), "parent");
    }

    @GetMapping("/getAllCategories")
    public String getAllCategories() throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findAll(), "parent");
    }

    @GetMapping("/getBookCategories/{bookId}")
    public String getBookCategories(@PathVariable long bookId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findBookCategories(bookId), "parent");
    }

    @GetMapping("/getCategoryWithChildren/{categoryId}")
    public String getCategoryWithChildren(@PathVariable long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "parent");
    }

    @GetMapping("/getCategoryWithParent/{categoryId}")
    public String getCategoryWithParent(@PathVariable long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "children");
    }

}
