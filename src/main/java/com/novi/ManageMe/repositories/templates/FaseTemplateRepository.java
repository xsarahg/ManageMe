package com.novi.ManageMe.repositories.templates;

import com.novi.ManageMe.models.templates.FaseTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaseTemplateRepository extends JpaRepository<FaseTemplate, Long> {
}
