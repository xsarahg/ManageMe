package com.novi.ManageMe.services.roadmap;

import com.novi.ManageMe.models.roadmap.Fase;
import com.novi.ManageMe.models.roadmap.SubFase;
import com.novi.ManageMe.repositories.roadmap.FaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class FaseService implements IPercentage {

    @Autowired
    FaseRepository faseRepository;

    @Autowired
    RoadmapService roadmapService;

    @Autowired
    SubFaseService subFaseService;

    public FaseService(FaseRepository faseRepository) {
        this.faseRepository = faseRepository;
    }

    public void save(Fase fase) {
        faseRepository.save(fase);
    }

    Optional<Fase> findById(Long id) {
        return faseRepository.findById(id);
    }

    public void changePercentage(Long faseId, Long roadmapId) {

        // define variables
        int numOfSubFases;
        int done = 0;
        int percentageDone;

        // get Fase by faseId
        Optional<Fase> fase = findById(faseId);

        // check if Fase is found
        if (fase.isPresent()) {

            // get subfases in Fase
            Set<SubFase> subfases = fase.get().getSubFases();

            // get number of subFases in Fase
            numOfSubFases = subfases.size();

            // loop through all subfases
            for (SubFase subFase: subfases) {
                // check if subFase is done
                if (subFase.isDone()) {
                    // if done, add 1 to variable done
                    done++;
                }
            }

            // calculate percentage done (IPercentage)
            percentageDone = getPercentageDone(numOfSubFases, done);

            // update percentage in fase
            faseRepository.updatePercentageDone(percentageDone, faseId);

            // set the percentage (this was needed, it did not work without this)
            fase.get().setPercentageDone(percentageDone);

            // check if percentage done is 100%
            if (percentageDone == 100) {

                // update done in fase
                faseRepository.updateDone(faseId);

                // set done
                fase.get().setDone(true);
            }

            // invoke method in roadmapService to recalculate it's percentage done
            roadmapService.changePercentage(roadmapId);
        }
    }
}

