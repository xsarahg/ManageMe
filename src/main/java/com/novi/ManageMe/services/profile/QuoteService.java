package com.novi.ManageMe.services.profile;

import com.novi.ManageMe.models.profile.Quote;
import com.novi.ManageMe.repositories.profile.QuoteRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {

    @Autowired
    QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public String save(Quote quote) {
        try {
            quoteRepository.save(quote);
            return "Quote has been saved!";
        } catch (Exception e) {
            return "Quote could not be saved";
        }
    }

    public Optional<Quote> findById(Long quoteId) {
        return quoteRepository.findById(quoteId);
    }

    public List<Quote> findByCategory(String categoryName) {
        return quoteRepository.findByCategory(categoryName);
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    // generate random quote by category
    public Quote getRandomQuoteByCategory(String categoryName) throws NotFoundException {
        List<Quote> quotes = findByCategory(categoryName);
        if (quotes.isEmpty()) {
            throw new NotFoundException("No quotes where found for this catagory"); // whatttt does this doooo
        }
        Random random = new Random();
        return quotes.get(random.nextInt(quotes.size())); // returns quote by using random indexnumber
    }

    public String deleteById(Long quoteId) {
        // check if quoteId is valid
        boolean present = quoteRepository.findById(quoteId).isPresent();
        if (present) {
            quoteRepository.deleteById(quoteId);
            return "Quote has been deleted!";
        } else {
            return "Could not find quote";
        }
    }

    public String updateQuote(Long quoteId, Quote quote) {
        // check if quoteId is valid
        boolean present = quoteRepository.findById(quoteId).isPresent();
        if (present) {

            // save attributes in variables so they can be used in the query in the repository
            String text = quote.getText();
            String author = quote.getAuthor();
            String category = quote.getCategory();

            // update quote in quoterepository
            quoteRepository.updateQuote(quoteId, text, author, category);
            return "Quote had been updated!";
        } else {
            return "Quote could not be found";
        }
    }
}
