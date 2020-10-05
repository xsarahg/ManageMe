package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.roadmap.Roadmap;
import com.novi.ManageMe.models.templates.RoadmapTemplate;
import com.novi.ManageMe.services.roadmap.RoadmapService;
import com.novi.ManageMe.services.roadmap.SubFaseService;
import com.novi.ManageMe.services.templates.RoadmapTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping("/api/user/roadmap")
public class RoadmapController {

    @Autowired
    private RoadmapService roadmapService;

    @Autowired
    private RoadmapTemplateService roadmapTemplateService;

    @Autowired
    private SubFaseService subFaseService;

    // TEMPLATE
    // get user's roadmaptemplate content
    // using roadmapTemplateId instead of user_id so in the future users may be able to have more than 1 roadmap
    @GetMapping(path = "/template")
    @PreAuthorize("hasRole('USER')")
    public Optional<RoadmapTemplate> getRoadmapTemplate(@Valid @RequestParam Long roadmapTemplateId) {
        return roadmapTemplateService.findById(roadmapTemplateId);
    }


    // if we used this, it is only possible for a user to have a single roadmap, so we are trying to work with the get request for the roadmap template that uses the roadmapTemplateId
//    @GetMapping(path = "/template/user")
//    @PreAuthorize("hasRole('USER')")
//    public Optional<RoadmapTemplate> getRoadmapTemplate(@Valid @RequestParam Long userId) {
//        return roadmapTemplateService.findByUserId(userId);
//    }


    // ROADMAP
    // get user's roadmap data
    // using roadmapId instead of user_id so in the future users may be able to have more than 1 roadmap
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public Optional<Roadmap> getRoadmap(@Valid @RequestParam Long roadmapId) {
        return roadmapService.findById(roadmapId);
    }

    @GetMapping(path = "/user")
    @PreAuthorize("hasRole('USER')")
    public Optional<Roadmap> getRoadmapByUserId(@Valid @RequestParam Long userId) {
        return roadmapService.findByUserId(userId);
    }


    // percentagea
    @GetMapping(path = "/percentage")
    @PreAuthorize("hasRole('USER')")
    public Number getRoadmapPercentageDone(@Valid @RequestParam Long userId) {
        return roadmapService.getRoadmapPercentageDone(userId);
    }

    @PutMapping(path = "/subfase/done")
    @PreAuthorize("hasRole('USER')")
    public String updateSubFaseIsDone(@Valid @RequestParam Long roadmapId, @Valid @RequestParam Long subFaseId) {
        return subFaseService.updateSubFaseisDone(roadmapId, subFaseId);
    }
}
