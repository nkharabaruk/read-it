package com.readit.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.readit.entity.Category;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    JsonPropertyFilterMixIn jsonFilter;

    @RequestMapping("/getRootCategories")
    public String getRootCategories() throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.getRootCategories(), "parent");
    }

    @RequestMapping("/getAllCategories")
    public String getAllCategories() throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.getAll(), "parent");
    }

    @RequestMapping("/getBookCategories/{bookId}")
    public String getBookCategories(@PathVariable Long bookId) throws JsonProcessingException {
        return jsonFilter.processObject(categoryService.getBookCategories(bookId), "children");
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
