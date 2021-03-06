package com.readit.service;

import com.readit.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Page<Book> findPage(Pageable pageable);

    Book findById(long id);

    List<Book> saveAll(List<Book> books);

    Book save(Book book);

    Book update(long id, Book book);

    void deleteAll();

    void delete(long id);
}
