package com.novi.ManageMe.controllers.artistPage;

import com.novi.ManageMe.models.page.Genre;
import com.novi.ManageMe.services.page.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/artist-page/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    // get genre by genreId
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<Genre> getGenre(@Valid @RequestParam Long genreId) {
        return genreService.findById(genreId);
    }

    // add genre
    @PostMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String addGenre(@Valid @RequestParam String name, @Valid @RequestParam String description, @Valid @RequestParam int rating, @Valid @RequestParam Long userId) {
        return genreService.save(name, description, rating, userId);
    }

    // delete genre
    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String deleteGenre(@Valid @RequestParam Long genreId) {
        return genreService.deleteById(genreId);
    }

    // update genre
    @PutMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String updateGenre(@Valid @RequestParam Long genreId, @Valid @RequestBody Genre genre) {
        return genreService.updateGenre(genreId, genre);
    }
}
