package com.novi.ManageMe.controllers.artistPage;

import com.novi.ManageMe.models.page.*;
import com.novi.ManageMe.services.page.ArtistPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/artist-page")
public class ArtistPageController {

    @Autowired
    ArtistPageService artistPageService;

    // get complete artistpage
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Optional<ArtistPage> getPage(@Valid @RequestParam Long userId) { // searching by userId because each user can have only one page
        return artistPageService.findByUser_id(userId);
    }

    // BIOGRAPHY
    // save biography
    @PostMapping(path = "/biography")
    @PreAuthorize("hasRole('USER')")
    public String saveBiography(@Valid @RequestParam String biography, @Valid @RequestParam Long userId) {
        return artistPageService.saveBiography(biography, userId);
    }

    // get biography
    @GetMapping(path = "/biography")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String getBiography(@Valid @RequestParam Long userId) {
        return artistPageService.getBiography(userId);
    }

    // delete biography
    @DeleteMapping(path = "/biography")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String deleteBiography(@Valid @RequestParam Long userId) {
        return artistPageService.deleteBiography(userId);
    }

    // update biography
    @PutMapping(path = "/biography")
    @PreAuthorize("hasRole('USER')")
    public String updateBiography(@Valid @RequestParam Long userId, @Valid @RequestBody String biography) {
        return artistPageService.updateBiography(userId, biography);
    }


    // GENRES
    // get genres
    @GetMapping(path = "genres")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Set<Genre> getGenresByUserId(@Valid @RequestParam Long userId) {
        return artistPageService.getGenres(userId);
    }


    // EXPERIENCES
    @GetMapping(path = "experiences")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Set<Experience> getExperiencesByUserId(@Valid @RequestParam Long userId) {
        return artistPageService.getExperiences(userId);
    }


    // NEWS
    @GetMapping(path = "news")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Set<NewsItem> getNewsItemsByUserId(@Valid @RequestParam Long userId) {
        return artistPageService.getNews(userId);
    }
}