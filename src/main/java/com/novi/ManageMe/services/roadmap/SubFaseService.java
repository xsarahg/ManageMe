package com.novi.ManageMe.services.roadmap;

import com.novi.ManageMe.models.roadmap.SubFase;
import com.novi.ManageMe.repositories.roadmap.SubFaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubFaseService {

    @Autowired
    private SubFaseRepository subFaseRepository;

    @Autowired
    private FaseService faseService;

    public void save(SubFase subFase) {
        subFaseRepository.save(subFase);
    }

    public Optional<SubFase> findById(Long id) {
        return subFaseRepository.findById(id);
    }

    public String updateSubFaseisDone(Long roadmapId, Long subFaseId) {
        // find SubFase by subFaseId
        Optional<SubFase> subFase = subFaseRepository.findById(subFaseId);

        // check if SubFase was found
        if (subFase.isPresent()) {
            // update done in SubFase
            subFaseRepository.updateDone(subFaseId);

            // set done (this was needed, it did not work without this, maybe because of the joined table)
            subFase.get().setDone(true);

            // get SubFase's faseId
            Long faseId = subFase.get().getFase_id();

            // let faseService change the percentageDone of the SubFase's Fase
            faseService.changePercentage(faseId, roadmapId);

            // return String that shows the SubFase is done
            return "SubFase is done!";
        } else {
            // return String that shows the SubFase is not found
            return "Could not find subFase..";
        }
    }
}
