package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.profile.Tutorial;
import com.novi.ManageMe.services.profile.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/tutorial")
public class TutorialController {

    @Autowired
    TutorialService tutorialService;

    // get tutorial by id
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<Tutorial> getTutorial(@Valid @RequestParam Long tutorialId) {
        return tutorialService.findById(tutorialId);
    }

    // get tutorials by category and string (title)
    @GetMapping(path = "/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Tutorial> getTutorialByCategoryAndName(@Valid @RequestParam String category, @Valid @RequestParam String searchString) {
        return tutorialService.findByCategoryAndName(category, searchString);
    }

    // get tutorials by category
    @GetMapping(path = "/find")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public List<Tutorial> getTutorialsByCategory(@Valid @RequestParam String category) {
        return tutorialService.findByCategory(category);
    }

    // get all tutorials
    @GetMapping(path = "/all")
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public List<Tutorial> getAllTutorials() {
        return tutorialService.findAll();
    }

    // save tutorial
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String saveTutorial(@Valid @RequestBody Tutorial tutorial) {
        return tutorialService.save(tutorial);
    }

    // delete tutorial
    @DeleteMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String deleteTutorial(@Valid @RequestParam Long tutorialId) {
        return tutorialService.deleteById(tutorialId);
    }

    // update existing tutorial
    @PutMapping
    @PreAuthorize("hasRole('ADMIN')") // method can be invoked by user with role ADMIN
    public String updateTutorial(@Valid @RequestParam Long tutorialId, @Valid @RequestBody Tutorial tutorial) {
        return tutorialService.updateTutorial(tutorialId, tutorial);
    }

}
