package com.readit.service.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Book;
import com.readit.entity.Category;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<Book> getFromCategoryAndDescendants(Long categoryId) {
        List<Book> books = new ArrayList<Book>();
        List<Category> categories = categoryService.getDescendants(categoryId);
        for (Category c : categories) {
            books.addAll(bookDAO.getFromCategory(c.getId()));
        }
        return books;
    }
}
