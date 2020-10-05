package com.novi.ManageMe.repositories.templates;

import com.novi.ManageMe.models.templates.SubFaseTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubFaseTemplateRepository extends JpaRepository<SubFaseTemplate, Long> {
}
