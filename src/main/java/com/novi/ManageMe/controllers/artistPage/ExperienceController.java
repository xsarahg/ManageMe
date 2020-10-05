package com.novi.ManageMe.controllers.artistPage;

import com.novi.ManageMe.models.page.Experience;
import com.novi.ManageMe.services.page.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/artist-page/experience")
public class ExperienceController {

    @Autowired
    ExperienceService experienceService;

    // get experience by experienceId
    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<Experience> getExperience(@Valid @RequestParam Long experienceId) {
        return experienceService.findById(experienceId);
    }

    // add experience
    @PostMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String addExperience(@Valid @RequestParam String name, @Valid @RequestParam String details, @Valid @RequestParam String institution, @Valid @RequestParam String place, @Valid @RequestParam String link, @Valid @RequestParam Long userId) {
        return experienceService.save(name, details, institution, place, link, userId);
    }

    // delete experience
    @DeleteMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String deleteExperience(@Valid @RequestParam Long experienceId) {
        return experienceService.deleteById(experienceId);
    }

    // update experience
    @PutMapping
    @PreAuthorize("hasRole('USER')") // method can be invoked by user with role USER
    public String updateExperience(@Valid @RequestParam Long experienceId, @Valid @RequestBody Experience experience) {
        return experienceService.updateExperience(experienceId, experience);
    }
}