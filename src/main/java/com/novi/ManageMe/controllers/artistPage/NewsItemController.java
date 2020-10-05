package com.novi.ManageMe.controllers.artistPage;

import com.novi.ManageMe.models.page.NewsItem;
import com.novi.ManageMe.services.page.NewsItemservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/artist-page/news-item")
public class NewsItemController {

    @Autowired
    NewsItemservice newsItemservice;

    // get news item by newsItemId
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<NewsItem> getNewsItem(@Valid @RequestParam Long newsItemId) {
        return newsItemservice.findById(newsItemId);
    }

    // add news item
    @PostMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String addNewsItem(@Valid @RequestParam String name, @Valid @RequestParam String description, @Valid @RequestParam String link, @Valid @RequestParam Long userId) {
        return newsItemservice.save(name, description, link, userId);
    }

    // delete news item
    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String deleteNewsItem(@Valid @RequestParam Long newsItemId) {
        return newsItemservice.deleteById(newsItemId);
    }

    // update news item
    @PutMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String updateNewsItem(@Valid @RequestParam Long newsItemId, @Valid @RequestBody NewsItem newsItem) {
        return newsItemservice.updateNewsItem(newsItemId, newsItem);
    }
}
