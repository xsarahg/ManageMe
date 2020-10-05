package com.novi.ManageMe.models.roadmap;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Getter  // lombok shortcut for inserting getters
@Setter // lombok shortcut for inserting setters
@MappedSuperclass // class whose mapping information is applied to the entities that inherit from the class
public abstract class Path {

    @Column(name = "done")
    protected boolean done;

    public Path(boolean done) {
        this.done = done;
    }
}
