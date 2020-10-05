package com.novi.ManageMe.services.page;

import com.novi.ManageMe.models.page.Experience;
import com.novi.ManageMe.repositories.page.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExperienceService {

    @Autowired
    ExperienceRepository experienceRepository;

    @Autowired
    ArtistPageService artistPageService;

    public ExperienceService(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    public String save(String name, String details, String institution, String place, String link, Long userId) {
        try {
            Experience experience = new Experience(name, details, institution, place, link);
            experienceRepository.save(experience);
            artistPageService.addExperience(experience.getId(), userId);
            return "Experience is saved!";
        } catch (Exception e) {
            return "Experience could not be saved";
        }
    }

    public Optional<Experience> findById(Long experienceId) {
        return experienceRepository.findById(experienceId);
    }

    public String deleteById(Long experienceId) {
        // check if experiencelId is valid
        boolean present = experienceRepository.findById(experienceId).isPresent();
        if (present) {
            artistPageService.deleteExperience(experienceId);
            experienceRepository.deleteById(experienceId);
            return "Experience is deleted!";
        } else {
            return "Could not find experience to delete";
        }
    }

    public String updateExperience(Long experienceId, Experience experience) {
        // check if experienceId is valid
        boolean present = experienceRepository.findById(experienceId).isPresent();
        if (present) {

            // save attributes in variables so they can be used in the query in the repository
            String name = experience.getName();
            String details = experience.getDetails();
            String institution = experience.getInstitution();
            String place = experience.getPlace();
            String link = experience.getLink();

            // update genre in genrerepository
            experienceRepository.updateExperience(experienceId, name, details, institution, place, link);

            return "Experience is updated!";
        } else {
            return "Could not update experience";
        }
    }
}
