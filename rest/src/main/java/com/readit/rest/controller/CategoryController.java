package com.readit.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.entity.Category;
import com.readit.rest.util.JsonPropertyFilterMixIn;
import com.readit.service.CategoryService;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/categories")
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

    @GetMapping("/getBookCategories/{bookId}")
    public String getBookCategories(@PathVariable long bookId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findBookCategories(bookId), "parent");
    }

    @GetMapping("/getCategoryWithChildren/{categoryId}")
    public String getCategoryWithChildren(@PathVariable long categoryId) throws JsonProcessingException, CategoryNotFoundException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "parent");
    }

    @GetMapping("/getCategoryWithParent/{categoryId}")
    public String getCategoryWithParent(@PathVariable long categoryId) throws JsonProcessingException, CategoryNotFoundException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "children");
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable long categoryId) throws CategoryNotFoundException {
        return categoryService.findById(categoryId);
    }

    @GetMapping
    public List<Category> getAllCategories() throws JsonProcessingException {
        return categoryService.findAll();
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) throws CategoryAlreadyExistsException {
        return categoryService.save(category);
    }

    @DeleteMapping
    public void deleteAllCategories() {
        categoryService.deleteAll();
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId) throws CategoryNotFoundException {
        categoryService.delete(categoryId);
    }

}
