package com.readit.controller;

import com.readit.entity.Quote;
import com.readit.entity.Book;
import com.readit.service.QuoteService;
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
        return  quoteService.findAll();
    }

    @GetMapping("/{quoteId}")
    public Quote getQuoteById(@PathVariable long quoteId) {
        return quoteService.findById(quoteId);
    }

    @PostMapping
    public List<Quote> saveQuotes(@RequestBody List<Quote> quotes) {
        return quoteService.saveAll(quotes);
    }

    @PostMapping
    public Quote saveQuote(@RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    @DeleteMapping
    public void deleteAllQuotes() {
        quoteService.deleteAll();
    }

    @DeleteMapping
    public void deleteQuote(@RequestBody Quote quote) {
        quoteService.delete(quote);
    }
}
