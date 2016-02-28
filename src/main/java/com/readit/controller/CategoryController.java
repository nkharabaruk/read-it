package com.readit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    JsonPropertyFilterMixIn jsonFilter;

    @RequestMapping("/getAllCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping("/getCategoryWithChildren/{categoryId}")
    public String getCategoryWithChildren(@PathVariable Long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.getById(categoryId), "parent");
    }

    @RequestMapping("/getCategoryWithParent/{categoryId}")
    public String getCategoryWithParent(@PathVariable Long categoryId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.getById(categoryId), "children");
    }

}
