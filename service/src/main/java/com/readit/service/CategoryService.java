package com.readit.service;

import com.readit.entity.Category;
import com.readit.service.exception.CategoryAlreadyExistsException;
import com.readit.service.exception.CategoryNotFoundException;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();

    Category findById(long id) throws CategoryNotFoundException;

    List<Category> findBookCategories(long bookId);

    List<Category> findRootCategories();

    List<Category> findAscendants(long id) throws CategoryNotFoundException;

    List<Category> findDescendants(long id) throws CategoryNotFoundException;

    List<Category> saveAll(List<Category> list);

    Category save(Category category) throws CategoryAlreadyExistsException;

    void deleteAll();

    void delete(long id) throws CategoryNotFoundException;
}
