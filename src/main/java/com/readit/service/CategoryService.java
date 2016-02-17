package com.readit.service;

import com.readit.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    List<Category> getRootCategories();

    List<Category> getParents(Long id);

    List<Category> getChildren(Long id);
}
