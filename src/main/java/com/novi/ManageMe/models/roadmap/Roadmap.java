package com.novi.ManageMe.models.roadmap;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "roadmaps") // specifies the details of the table that will be used to create the table in the database
public class Roadmap extends Path {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="roadmap")
    @Column(name = "id")
    private Long id;

    @Column(name = "roadmap_name")
    @Size(max = 30)
    private String roadmapName;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "type")
    @Size(max = 30)
    private String type;

    @Column(name = "roadmap_template_id")
    private Long roadmapTemplateId;

    @Column(name = "percentage_done")
    private int percentageDone;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "roadmap_fases",
            joinColumns = @JoinColumn(name = "roadmap_id"),
            inverseJoinColumns = @JoinColumn(name = "fase_id"))
    private Set<Fase> fases = new HashSet<>();

    public Roadmap(String roadmapName, Long user_id, String type, Long roadmapTemplateId) {
        this.roadmapName = roadmapName;
        this.user_id = user_id;
        this.type = type;
        this.roadmapTemplateId = roadmapTemplateId;
        this.done = false;
        this.percentageDone = 0;
    }
}
