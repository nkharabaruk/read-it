package com.readit.service.impl;

import com.readit.entity.Quote;
import com.readit.repository.QuoteRepository;
import com.readit.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteServiceImpl implements QuoteService {

    private final QuoteRepository quoteRepository;

    @Autowired
    public QuoteServiceImpl(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @Override
    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    @Override
    public Quote findById(long id) {
        return quoteRepository.findOne(id);
    }

    @Override
    public List<Quote> saveAll(List<Quote> list) {
        return quoteRepository.save(list);
    }

    @Override
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public void deleteAll() {
        quoteRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        quoteRepository.delete(id);
    }
}
