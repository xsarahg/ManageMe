package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.profile.Quote;
import com.novi.ManageMe.services.profile.QuoteService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/quote")
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    // get quote by id
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<Quote> getQuote(@Valid @RequestParam Long quoteId) {
        return quoteService.findById(quoteId);
    }

    // get quotes by category
    @GetMapping(path = "/find")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Quote> getQuotesByCategory(@Valid @RequestParam String category) {
        return quoteService.findByCategory(category);
    }

    // get all quotes
    @GetMapping(path = "/all")
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public List<Quote> getAllQuotes() {
        return quoteService.findAll();
    }

    // get random quote by category
    @GetMapping(path = "/random")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Quote getRandomQuoteByCategory(@Valid @RequestParam String categoryName) throws NotFoundException { // throws exception if no quote with given category was found
        return quoteService.getRandomQuoteByCategory(categoryName);
    }

    // save quote
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String saveQuote(@Valid @RequestBody Quote quote) {
        return quoteService.save(quote);
    }

    // delete quote
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String deleteQuote(@Valid @RequestParam Long quoteId) {
        return quoteService.deleteById(quoteId);
    }

    // update existing quote
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String updateQuote(@Valid @RequestParam Long quoteId, @RequestBody Quote quote) {
        return quoteService.updateQuote(quoteId, quote);
    }
}
