package com.novi.ManageMe.services.page;

import com.novi.ManageMe.models.page.*;
import com.novi.ManageMe.repositories.page.ArtistPageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
public class ArtistPageService {

    @Autowired
    private ArtistPageRepository artistPageRepository;


    public ArtistPageService(ArtistPageRepository artistPageRepository) {
        this.artistPageRepository = artistPageRepository;
    }

    // ARTISTPAGE
    public void save(ArtistPage artistPage) {
        artistPageRepository.save(artistPage);
    }

    public Optional<ArtistPage> findById(Long artistPageId) {
        return artistPageRepository.findById(artistPageId);
    }

    public Optional<ArtistPage> findByUser_id(Long userId) {
        return artistPageRepository.findByUser_id(userId);
    }


    // BIOGRAPHY
    public String saveBiography(String biography, Long userId) {
        if (biography.length() <= 1000) {
            Optional<ArtistPage> artistPage = findByUser_id(userId);
            if (artistPage.isPresent()) {
                artistPage.get().setBiography(biography);
                artistPageRepository.setBiography(biography, userId);
                return "Biography is saved!";
            } else {
                return "Could not find artist page";
            }
        } else {
            System.out.println("length: " + biography.length());
            return "Biography must be less than 1000 characters";
        }
    }

    public String getBiography(Long userId) {
        Optional<ArtistPage> artistPage = findByUser_id(userId);
        if (artistPage.isPresent()) {
            String bio = artistPage.get().getBiography();
            if (bio.isEmpty()) {
                return "No biography was found";
            } else {
                return bio;
            }
        } else {
            return "No artist page was found";
        }
    }

    public String deleteBiography(Long userId) {
        Optional<ArtistPage> artistPage = artistPageRepository.findByUser_id(userId);
        if (artistPage.isPresent()) {
            artistPage.get().setBiography("");
            return "Biography has been deleted";
        } else {
            return "Could not find biography";
        }
    }

    public String updateBiography(Long userId, String biography) {
        Optional<ArtistPage> artistPage = artistPageRepository.findByUser_id(userId);
        if (artistPage.isPresent()) {
            artistPage.get().setBiography(biography);
            return "Biography has been updated";
        } else {
            return "Could not find biography to update";
        }
    }


    // GENRE
    public void addGenre(Long genreId, Long userId) {
        artistPageRepository.addGenre(genreId, findByUser_id(userId).get().getId());
    }


    public Set<Genre> getGenres(Long userId) {
        Optional<ArtistPage> artistPage = findByUser_id(userId);
        return artistPage.map(ArtistPage::getGenres).orElse(null);
    }

    public void deleteGenre(Long genreId) {
        artistPageRepository.deleteGenreById(genreId);
    }


    // EXPERIENCE
    public void addExperience(Long experienceId, Long userId) {
        artistPageRepository.addExperience(experienceId, findByUser_id(userId).get().getId());
    }

    public Set<Experience> getExperiences(Long userId) {
        Optional<ArtistPage> artistPage = findByUser_id(userId);
        return artistPage.map(ArtistPage::getExperiences).orElse(null);
    }

    public void deleteExperience(Long experienceId) {
        artistPageRepository.deleteExperienceById(experienceId);
    }


    // NEWS ITEM
    public void addNewsItem(Long newsItemId, Long userId) {
        artistPageRepository.addNewsItem(newsItemId, findByUser_id(userId).get().getId());
    }

    public Set<NewsItem> getNews(Long userId) {
        Optional<ArtistPage> artistPage = findByUser_id(userId);
        return artistPage.map(ArtistPage::getNews).orElse(null);
    }

    public void deleteNewsItem(Long newsItemId) {
        artistPageRepository.deleteNewsItemById(newsItemId);
    }



    // PHOTO
    public String addPhotoId(Long photoId, Long userId) {
        try {
            artistPageRepository.addPhotoId(photoId, findByUser_id(userId).get().getId());
            return "Photo id is saved!";
        } catch (Exception e) {
            return "Photo id couldn't be saved..";
        }
    }


    // TRACK
    public String addTrackId(Long trackId, Long userId) {
        try {
            artistPageRepository.addTrackId(trackId, findByUser_id(userId).get().getId());
            return "Track is saved!";
        } catch (Exception e) {
            return "Track id couldn't be saved..";
        }
    }
}
