package com.novi.ManageMe.services.roadmap;

import com.novi.ManageMe.models.roadmap.Fase;
import com.novi.ManageMe.models.roadmap.Roadmap;
import com.novi.ManageMe.repositories.roadmap.RoadmapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RoadmapService implements IPercentage {

    @Autowired
    RoadmapRepository roadmapRepository;

    public RoadmapService(RoadmapRepository roadmapRepository) {
        this.roadmapRepository = roadmapRepository;
    }

    public void save(Roadmap roadmap) {
        roadmapRepository.save(roadmap);
    }

    public Optional<Roadmap> findById(Long id) {
        return roadmapRepository.findById(id);
    }

    public Optional<Roadmap> findByUserId(Long userId) {
        return roadmapRepository.findByUserId(userId);
    }

    public Number getRoadmapPercentageDone(Long userId) {
        Optional<Roadmap> roadmap = roadmapRepository.findByUserId(userId);
        if (roadmap.isPresent()) {
            return roadmap.get().getPercentageDone();
        } else {
            return null;
        }
    }

    public void changePercentage(Long roadmapId) {
        // define variables
        int numOfFases;
        int done = 0;
        int percentageDone;

        // get roadmap by roadmapId
        Optional<Roadmap> roadmap = findById(roadmapId);

        // check if roadmap is found
        if (roadmap.isPresent()) {

            // get fases in roadmap
            Set<Fase> fases = roadmap.get().getFases();

            // get number of fases in roadmap
            numOfFases = fases.size();

            // loop through all fases
            for (Fase fase : fases) {
                // check if fase is done
                if (fase.isDone()) {
                    // if fase is done, add 1 to variable done
                    done++;
                }
            }

            // calculate percentage done (IPercentage)
            percentageDone = getPercentageDone(numOfFases, done);

            // update percentage in roadmap
            roadmapRepository.updatePercentageDone(percentageDone, roadmapId);

            // set the percentage (this was needed, it did not work without this)
            roadmap.get().setPercentageDone(percentageDone);

            // check if percentage done is 100%
            if (percentageDone == 100) {

                // update done in fase
                roadmapRepository.updateDone(roadmapId);

                // set done
                roadmap.get().setDone(true);
            }
        }
    }
}