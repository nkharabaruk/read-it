package com.readit.service;

import com.readit.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    List<Category> findRootCategories();

    List<Category> saveAll(List<Category> list);

    Category save(Category category);

    Category update(long id, Category category);

    void deleteAll();

    void delete(long id);
}
