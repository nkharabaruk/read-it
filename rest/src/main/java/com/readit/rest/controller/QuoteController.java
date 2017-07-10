package com.readit.rest.controller;

import com.readit.entity.Quote;
import com.readit.service.QuoteService;
import com.readit.service.exception.QuoteNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quotes")
public class QuoteController {

    private final QuoteService quoteService;

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping
    public List<Quote> getAllQuotes() {
        return quoteService.findAll();
    }

    @GetMapping("/{quoteId}")
    public Quote getQuoteById(@PathVariable long quoteId) {
        return quoteService.findById(quoteId);
    }

    @PostMapping
    public Quote saveQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @PutMapping("/{quoteId}")
    public Quote updateQuote(@PathVariable long quoteId, @RequestBody Quote quote) throws QuoteNotFoundException {
        return quoteService.update(quoteId, quote);
    }

    @DeleteMapping
    public void deleteAllQuotes() {
        quoteService.deleteAll();
    }

    @DeleteMapping("/{quoteId}")
    public void deleteQuote(@PathVariable long quoteId) {
        quoteService.delete(quoteId);
    }
}
