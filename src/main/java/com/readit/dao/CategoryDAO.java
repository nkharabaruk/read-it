package com.readit.dao;

import com.readit.entity.Book;
import com.readit.entity.Category;

import java.util.List;
import java.util.Set;

public interface CategoryDAO {
    List<Category> list();

    Category get(Long id);

    void saveOrUpdate(Category category);

    void delete(Long id);

    List<Category> getRootCategories ();

    List<Book> getBooksFromCategory(Long categoryId);
}
