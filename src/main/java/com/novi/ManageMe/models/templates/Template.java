package com.novi.ManageMe.models.templates;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Getter  // lombok shortcut for inserting getters
@Setter // lombok shortcut for inserting setters
@MappedSuperclass // class whose mapping information is applied to the entities that inherit from the class
public abstract class Template {

    @Column(name = "title")
    @Size(max = 30)
    protected String title;

    @Column(name = "description")
    @Size(max = 500)
    protected String description;

    public Template(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
