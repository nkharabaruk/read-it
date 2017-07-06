package com.readit.service;

import com.readit.entity.Book;
import com.readit.service.exception.BookAlreadyExistsException;
import com.readit.service.exception.BookNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Page<Book> findPage(Pageable pageable);

    Book findById(long id) throws BookNotFoundException;

    List<Book> saveAll(List<Book> books);

    Book save(Book book) throws BookAlreadyExistsException;

    void deleteAll();

    void delete(long id) throws BookNotFoundException;
}
