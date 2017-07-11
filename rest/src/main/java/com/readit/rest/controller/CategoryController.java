package com.readit.rest.controller;

import com.readit.entity.Category;
import com.readit.rest.dto.CategoryDTO;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/categories")
public class CategoryController {

    private final CategoryService categoryService;
    private final BookService bookService;

    @Autowired
    public CategoryController(CategoryService categoryService, BookService bookService) {
        this.categoryService = categoryService;
        this.bookService = bookService;
    }

    @GetMapping("/{categoryId}")
    public CategoryDTO getCategoryById(@PathVariable long categoryId) throws CategoryNotFoundException {
        return CategoryDTO.from(categoryService.findById(categoryId));
    }

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.findAll().stream().map(CategoryDTO::from).collect(Collectors.toList());
    }

    @PostMapping
    public CategoryDTO saveCategory(@RequestBody CategoryDTO categoryDTO) throws CategoryAlreadyExistsException {
        return CategoryDTO.from(categoryService.save(mapToCategory(categoryDTO)));
    }

    @PutMapping("/{categoryId}")
    public CategoryDTO updateCategory(@PathVariable long categoryId, @RequestBody CategoryDTO categoryDTO) throws CategoryNotFoundException {
        return CategoryDTO.from(categoryService.update(categoryId, mapToCategory(categoryDTO)));
    }

    @DeleteMapping
    public void deleteAllCategories() {
        categoryService.deleteAll();
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable long categoryId) throws CategoryNotFoundException {
        categoryService.delete(categoryId);
    }

    private Category mapToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setChildren(categoryDTO.getChildren().stream().map(this::mapToCategory).collect(Collectors.toList()));
        category.setBooks(categoryDTO.getBooks().stream().map(bookService::findById).collect(Collectors.toList()));
        return category;
    }
}
