package com.readit.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.controller.exception.AlreadyExistsException;
import com.readit.controller.exception.NotFoundException;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
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
        try {
            return jsonFilter.processObject(categoryService.findById(categoryId), "parent");
        } catch (CategoryNotFoundException e) {
            throw new NotFoundException("Category doesn`t exist.");
        }
    }

    @GetMapping("/getCategoryWithParent/{categoryId}")
    public String getCategoryWithParent(@PathVariable long categoryId) throws JsonProcessingException {
        try {
            return jsonFilter.processObject(categoryService.findById(categoryId), "children");
        } catch (CategoryNotFoundException e) {
            throw new NotFoundException("Category doesn`t exist.");
        }
    }

    @GetMapping("/{categoryId}")
    public Category getCategoryById(@PathVariable long categoryId) {
        try {
            return categoryService.findById(categoryId);
        } catch (CategoryNotFoundException e) {
            throw new NotFoundException("Category doesn`t exist.");
        }
    }

    @PostMapping
    public Category saveCategory(@RequestBody Category category) {
        try {
            return categoryService.save(category);
        } catch (CategoryAlreadyExistsException e) {
            throw new AlreadyExistsException("Category already exists.");
        }
    }

    @DeleteMapping("/all")
    public void deleteAllCategorys() {
        categoryService.deleteAll();
    }

    @DeleteMapping
    public void deleteCategory(@RequestBody Category category) {
        try {
            categoryService.delete(category);
        } catch (CategoryNotFoundException e) {
            throw new NotFoundException("Category doesn`t exist.");
        }
    }

}
