package com.novi.ManageMe.services.templates;

import com.novi.ManageMe.repositories.templates.FaseTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FaseTemplateService {

    @Autowired
    FaseTemplateRepository faseTemplateRepository;

    public FaseTemplateService(FaseTemplateRepository faseTemplateRepository) {
        this.faseTemplateRepository = faseTemplateRepository;
    }
}
