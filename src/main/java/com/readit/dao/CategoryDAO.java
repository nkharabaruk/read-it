package com.readit.dao;

import com.readit.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> list();

    Category get(Long id);

    List<Category> getRootCategories();

    void saveOrUpdate(Category category);

    void delete(Long id);
}
