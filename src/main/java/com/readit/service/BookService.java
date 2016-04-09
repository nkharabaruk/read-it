package com.readit.service;

import com.readit.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookService {

    List<Book> getAll();

    Book getById(Long id);

    List<Book> getByAuthor(Long authorId);

    List<Book> getFromCategory(Long categoryId);

    Set<Book> getFromCategoryAndDescendants(Long categoryId);
}
