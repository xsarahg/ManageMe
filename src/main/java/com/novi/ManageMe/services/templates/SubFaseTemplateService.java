package com.novi.ManageMe.services.templates;

import com.novi.ManageMe.repositories.templates.SubFaseTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubFaseTemplateService {

    @Autowired
    SubFaseTemplateRepository subFaseTemplateRepository;

    public SubFaseTemplateService(SubFaseTemplateRepository subFaseTemplateRepository) {
        this.subFaseTemplateRepository = subFaseTemplateRepository;
    }
}
