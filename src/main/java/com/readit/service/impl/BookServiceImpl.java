package com.readit.service.impl;

import com.readit.dao.BookDAO;
import com.readit.entity.Author;
import com.readit.entity.Book;
import com.readit.entity.Category;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<Book> getFromCategoryAndParents(Long categoryId) {
//        Set<Book> books = categoryService.getById(categoryId).getBooks();
//        Set<Category> categories = categoryService.getChildren(categoryId);
//        for (Category c : categories) {
//            books.addAll(c.getBooks());
//        }
//        return books;
        return null;
    }
}
