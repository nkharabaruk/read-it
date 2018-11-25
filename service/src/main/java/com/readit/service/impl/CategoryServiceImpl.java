package com.readit.service.impl;

import com.readit.entity.Category;
import com.readit.repository.BookRepository;
import com.readit.repository.CategoryRepository;
import com.readit.service.CategoryService;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Override
    public List<Category> findRootCategories() {
        return categoryRepository.findByParent(null);
    }

    @Override
    public List<Category> saveAll(List<Category> list) {
        return categoryRepository.saveAll(list);
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
        try {
            Category existing = categoryRepository.getOne(id);
            category.setId(existing.getId());
            return categoryRepository.save(category);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new CategoryNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            categoryRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new CategoryNotFoundException(id);
        }
    }
}
