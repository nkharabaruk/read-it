package com.readit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private final CategoryService categoryService;
    private final JsonPropertyFilterMixIn jsonFilter;

    @Autowired
    public CategoryController(CategoryService categoryService, JsonPropertyFilterMixIn jsonFilter) {
        this.categoryService = categoryService;
        this.jsonFilter = jsonFilter;
    }

    @GetMapping("/getAllCategories")
    public String getAllCategories() throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findAll(), "parent");
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
    public String getCategoryWithChildren(@PathVariable long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "parent");
    }

    @GetMapping("/getCategoryWithParent/{categoryId}")
    public String getCategoryWithParent(@PathVariable long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.findById(categoryId), "children");
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable long categoryId) {
        return categoryService.findById(categoryId);
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/all")
    public void deleteAllCategorys() {
        categoryService.deleteAll();
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody Category category) {
        categoryService.delete(category);
    }

}
