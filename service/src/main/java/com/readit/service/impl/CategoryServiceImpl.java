package com.readit.service.impl;

import com.readit.entity.Category;
import com.readit.repository.BookRepository;
import com.readit.repository.CategoryRepository;
import com.readit.service.CategoryService;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final BookRepository bookRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, BookRepository bookRepository) {
        this.categoryRepository = categoryRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        Category category = categoryRepository.findOne(id);
        if (category == null) throw new CategoryNotFoundException(id);
        return category;
    }

    @Override
    public List<Category> findRootCategories() {
        return categoryRepository.findByParent(null);
    }

    @Override
    public List<Category> saveAll(List<Category> list) {
        return categoryRepository.save(list);
    }

    @Override
    public Category save(Category category) {
        List<Category> existing = categoryRepository.findByName(category.getName());
        if (!existing.isEmpty() && existing.contains(category)) {
            throw new CategoryAlreadyExistsException(existing.get(0));
        } else {
            return categoryRepository.save(category);
        }
    }

    @Override
    public Category update(long id, Category category) {
        Category existing = categoryRepository.findOne(id);
        if (existing == null) throw new CategoryNotFoundException(id);
        category.setId(existing.getId());
        return categoryRepository.save(category);
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            categoryRepository.delete(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(id);
        }
    }
}
