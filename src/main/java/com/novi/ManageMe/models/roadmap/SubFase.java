package com.novi.ManageMe.models.roadmap;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "subfases") // specifies the details of the table that will be used to create the table in the database
public class SubFase extends Path{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="subFase")
    @Column(name = "id")
    private Long id;

    @Column(name = "fase_id")
    private Long fase_id;

    public SubFase(Long fase_id) {
        this.fase_id = fase_id;
        this.done = false;
    }
}
