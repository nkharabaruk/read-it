package com.readit.service.impl;

import com.readit.entity.Book;
import com.readit.repository.BookRepository;
import com.readit.service.BookService;
import com.readit.service.CategoryService;
import com.readit.service.exception.BookAlreadyExistsException;
import com.readit.service.exception.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.categoryService = categoryService;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findPage(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    @Override
    public Book findById(long id) throws BookNotFoundException {
        Book book = bookRepository.findOne(id);
        if (book == null) {
            throw new BookNotFoundException();
        } else {
            return book;
        }
    }

    @Override
    public List<Book> saveAll(List<Book> books) {
        return bookRepository.save(books);
    }

    @Override
    public Book save(Book book) throws BookAlreadyExistsException {
        if (bookRepository.findByTitleAndYearOfRelease(book.getTitle(), book.getYearOfRelease()).get(0).equals(book)) {
            throw new BookAlreadyExistsException();
        } else {
            return bookRepository.save(book);
        }
    }

    @Override
    public void deleteAll() {
        bookRepository.deleteAll();
    }

    @Override
    public void delete(long id) throws BookNotFoundException {
        if (bookRepository.findOne(id) != null) {
            bookRepository.delete(id);
        } else {
            throw new BookNotFoundException();
        }
    }
}
