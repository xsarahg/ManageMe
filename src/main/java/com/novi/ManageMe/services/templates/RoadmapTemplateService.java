package com.novi.ManageMe.services.templates;

import com.novi.ManageMe.models.templates.RoadmapTemplate;
import com.novi.ManageMe.repositories.templates.RoadmapTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoadmapTemplateService {

    @Autowired
    private RoadmapTemplateRepository roadmapTemplateRepository;

    public RoadmapTemplateService(RoadmapTemplateRepository roadmapTemplateRepository) {
        this.roadmapTemplateRepository = roadmapTemplateRepository;
    }

    public Optional<RoadmapTemplate> findByName(String category) {
        return roadmapTemplateRepository.findByName(category);
    }

    public Optional<RoadmapTemplate> findById(Long roadmapTemplateId) {
        return roadmapTemplateRepository.findById(roadmapTemplateId);
    }
}
