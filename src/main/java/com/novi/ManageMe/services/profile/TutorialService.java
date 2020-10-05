package com.novi.ManageMe.services.profile;

import com.novi.ManageMe.models.profile.Tutorial;
import com.novi.ManageMe.repositories.profile.TutorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TutorialService {

    @Autowired
    TutorialRepository tutorialRepository;

    public TutorialService(TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    public String save(Tutorial tutorial) {
        try {
            tutorialRepository.save(tutorial);
            return "Tutorial has been saved!";
        } catch (Exception e) {
            return "Tutorial could not be saved";
        }
    }

    public Optional<Tutorial> findById(Long tutorialId) {
        return tutorialRepository.findById(tutorialId);
    }

    public List<Tutorial> findByCategory(String categoryName) {
        return tutorialRepository.findByCategory(categoryName);
    }

    public List<Tutorial> findAll() {
        return tutorialRepository.findAll();
    }

    public List<Tutorial> findByCategoryAndName(String category, String searchString) {
        return tutorialRepository.findByCategoryAndName(category, searchString);
    }

    public String deleteById(Long tutorialId) {

        // check if tutorialId is valid
        boolean present = tutorialRepository.findById(tutorialId).isPresent();
        if (present) {
            tutorialRepository.deleteById(tutorialId);
            return "Deleted tutorial!";
        } else {
            return "Could not find tutorial";
        }
    }

    public String updateTutorial(Long tutorialId, Tutorial tutorial) {

        // check if tutorialId is valid
        boolean present = tutorialRepository.findById(tutorialId).isPresent();
        if (present) {

            // save attributes in variables so they can be used in the query in the repository
            String title = tutorial.getTitle();
            String description = tutorial.getDescription();
            String category = tutorial.getCategory();
            String link = tutorial.getLink();

            // update tutorial in tutorialrepository
            tutorialRepository.updateTutorial(tutorialId, title, description, category, link);

            return "Tutorial has been updated!";

        } else {
            return "Could not find tutorial to update";
        }

    }
}
