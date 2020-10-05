package com.novi.ManageMe.services.page;

import com.novi.ManageMe.models.page.NewsItem;
import com.novi.ManageMe.repositories.page.NewsItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NewsItemservice {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private ArtistPageService artistPageService;

    public NewsItemservice(NewsItemRepository newsItemRepository) {
        this.newsItemRepository= newsItemRepository;
    }

    public String save(String name, String description, String link, Long userId) {
        try {
            NewsItem newsItem = new NewsItem(name, description, link);
            newsItemRepository.save(newsItem);
            artistPageService.addNewsItem(newsItem.getId(), userId);
            return "News item is saved!";
        } catch (Exception e) {
            return "News item could not be saved";
        }
    }

    public Optional<NewsItem> findById(Long newsItemId) {
        return newsItemRepository.findById(newsItemId);
    }

    public String deleteById(Long newsItemId) {
        // check if NewsItemId is valid
        boolean present = newsItemRepository.findById(newsItemId).isPresent();
        if (present) {
            artistPageService.deleteNewsItem(newsItemId);
            newsItemRepository.deleteById(newsItemId);
            return "News item is deleted!";
        } else {
            return "Could not find news item to delete";
        }

    }

    public String updateNewsItem(Long newsItemId, NewsItem newsItem) {
        // check if experienceId is valid
        boolean present = newsItemRepository.findById(newsItemId).isPresent();
        if (present) {

            // save attributes in variables so they can be used in the query in the repository
            String name = newsItem.getName();
            String description = newsItem.getDescription();
            String link = newsItem.getLink();

            // update genre in newsitemrepository
            newsItemRepository.updateNewsItem(newsItemId, name, description, link);

            return "News item is updated!";
        } else {
            return "Could not update news item";
        }

    }
}
