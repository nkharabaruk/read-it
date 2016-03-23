package com.readit.service;

import com.readit.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<Category> getAll();

    Category getById(Long id);

    Set<Category> getBookCategories(Long bookId);

    List<Category> getRootCategories();

    List<Category> getAscendants(Long id);

    List<Category> getDescendants(Long id);
}
