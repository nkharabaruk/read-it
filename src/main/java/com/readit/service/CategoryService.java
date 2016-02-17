package com.readit.service;

import com.readit.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    List<Category> getRootCategories();

    List<Category> getParents(Long id);

    Set<Category> getChildren(Long id);
}
