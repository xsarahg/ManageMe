package com.novi.ManageMe.repositories.templates;

import com.novi.ManageMe.models.templates.RoadmapTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoadmapTemplateRepository extends JpaRepository<RoadmapTemplate, Long> {

    Optional<RoadmapTemplate> findByName(String name);
}
