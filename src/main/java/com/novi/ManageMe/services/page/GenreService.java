package com.novi.ManageMe.services.page;

import com.novi.ManageMe.models.page.Genre;
import com.novi.ManageMe.repositories.page.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    ArtistPageService artistPageService;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public String save(String name, String description, int rating, Long userId) {
        try {
            Genre genre = new Genre(name, description, rating);
            genreRepository.save(genre);
            artistPageService.addGenre(genre.getId(), userId);
            return "Genre is saved!";
        } catch (Exception e) {
            return "Genre could not be saved";
        }
    }

    public Optional<Genre> findById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    public String deleteById(Long genreId) {
        // check if genreId is valid
        boolean present = genreRepository.findById(genreId).isPresent();
        if (present) {
            artistPageService.deleteGenre(genreId);
            genreRepository.deleteById(genreId);
            return "Genre is deleted!";
        } else {
            return "Could not find genre to delete";
        }
    }

    public String updateGenre(Long genreId, Genre genre) {
        // check if experienceId is valid
        boolean present = genreRepository.findById(genreId).isPresent();
        if (present) {

            // save attributes in variables so they can be used in the query in the repository
            String name = genre.getName();
            String description = genre.getDescription();
            int rating = genre.getRating();

            // update genre in genrerepository
            genreRepository.updateGenre(genreId, name, description, rating);

            return "Genre is updated!";
        } else {
            return "Could not update genre";
        }
    }
}
