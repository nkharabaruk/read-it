package com.readit.service.impl;

import com.readit.entity.Quote;
import com.readit.repository.QuoteRepository;
import com.readit.service.QuoteService;
import com.readit.service.exception.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
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
        return quoteRepository.findById(id).orElseThrow(() -> new QuoteNotFoundException(id));
    }

    @Override
    public List<Quote> saveAll(List<Quote> list) {
        return quoteRepository.saveAll(list);
    }

    @Override
    public Quote save(Quote quote) {
        return quoteRepository.save(quote);
    }

    @Override
    public Quote update(long id, Quote quote) {
        try {
            Quote existing = quoteRepository.getOne(id);
            quote.setId(existing.getId());
            return quoteRepository.save(quote);
        } catch (JpaObjectRetrievalFailureException e) {
            throw new QuoteNotFoundException(id);
        }
    }

    @Override
    public void deleteAll() {
        quoteRepository.deleteAll();
    }

    @Override
    public void delete(long id) {
        try {
            quoteRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new QuoteNotFoundException(id);
        }
    }
}
