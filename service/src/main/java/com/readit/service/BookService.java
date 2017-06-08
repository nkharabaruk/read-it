package com.readit.service;

import com.readit.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    Page<Book> getBookPage(Pageable pageable);

    Book getById(Long id);

    List<Book> saveAll(List<Book> books);

    Book save(Book book);
}
