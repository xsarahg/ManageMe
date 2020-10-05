package com.novi.ManageMe.models.roadmap;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "fases") // specifies the details of the table that will be used to create the table in the database
public class Fase extends Path {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="fase")
    @Column(name = "fase_id")
    private Long id;

    @Column(name = "roadmap_id")
    private Long roadmap_id;

    @Column(name = "percentage_done")
    private int percentageDone;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "fase_subfases",
            joinColumns = @JoinColumn(name = "subfase_id"),
            inverseJoinColumns = @JoinColumn(name = "fase_id"))
    private Set<SubFase> subFases = new HashSet<>();

    public Fase(Long roadmap_id) {
        this.roadmap_id = roadmap_id;
        this.done = false;
        this.percentageDone = 0;
    }
}
