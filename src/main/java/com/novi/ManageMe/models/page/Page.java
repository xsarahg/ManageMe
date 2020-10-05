package com.novi.ManageMe.models.page;

import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Getter  // lombok shortcut for inserting getters
@Setter // lombok shortcut for inserting setters
@MappedSuperclass // class whose mapping information is applied to the entities that inherit from the class
public abstract class Page {

    @Column(unique=true, name = "user_id")
    protected Long user_id;

    @Column(unique=true, name = "roadmap_id")
    protected Long roadmapId;

    public Page(Long user_id, Long roadmapId) {
        this.user_id = user_id;
        this.roadmapId = roadmapId;
    }
}
