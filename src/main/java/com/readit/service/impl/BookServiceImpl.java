package com.readit.service.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Book;
import com.readit.entity.Category;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDAO bookDAO;

    @Autowired
    CategoryService categoryService;

    public List<Book> getAll() {
        return bookDAO.list();
    }

    public Book getById(Long id) {
        return bookDAO.get(id);
    }

    public List<Book> getByAuthor(Long authorId) {
        return bookDAO.getByAuthor(authorId);
    }

    public List<Book> getFromCategory(Long categoryId) {
        return bookDAO.getFromCategory(categoryId);
    }

    public Set<Book> getFromCategoryAndDescendants(Long categoryId) {
        Set<Book> books = new HashSet<Book>();
        books.addAll(bookDAO.getFromCategory(categoryId));
        List<Category> categories = categoryService.getDescendants(categoryId);
        for (Category c : categories) {
            books.addAll(bookDAO.getFromCategory(c.getId()));
        }
        return books;
    }
}
