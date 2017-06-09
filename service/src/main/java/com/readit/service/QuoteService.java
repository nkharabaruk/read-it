package com.readit.service;

import com.readit.entity.Quote;
import org.springframework.stereotype.Service;

import java.util.List;

public interface QuoteService {

    List<Quote> findAll();

    Quote findById(long id);

    List<Quote> saveAll(List<Quote> list);

    Quote save(Quote quote);

    void deleteAll();

    void delete(Quote quote);
}
