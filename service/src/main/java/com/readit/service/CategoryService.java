package com.readit.service;

import com.readit.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id);

    List<Category> findBookCategories(long bookId);

    List<Category> findRootCategories();

    List<Category> findAscendants(long id);

    List<Category> findDescendants(long id);

    List<Category> saveAll(List<Category> list);

    Category save(Category category);

    void deleteAll();

    void delete(long id);
}
