package com.readit.service;

import com.readit.entity.Book;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface BookService {

    Book getById(Long id);

    Collection<Book> getAll();

    Collection<Book> getByAuthor(Long authorId);

    Collection<Book> getFromCategory(Long categoryId);

    Collection<Book> getFromCategoryAndDescendants(Long categoryId);

    void save(Book book);

    void saveAll(Collection<Book> books);
}
