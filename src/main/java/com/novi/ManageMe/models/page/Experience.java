package com.novi.ManageMe.models.page;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "experiences") // specifies the details of the table that will be used to create the table in the database
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 30)
    private String name;

    @Column(name = "details")
    @Size(max = 100)
    private String details;

    @Column(name = "institution")
    @Size(max = 30)
    private String institution;

    @Column(name = "place")
    @Size(max = 20)
    private String place;

    @Column(name = "link")
    @Size(max = 100)
    private String link;

    public Experience(String name, String details, String institution, String place,  String link) {
        this.name = name;
        this.details = details;
        this.institution = institution;
        this.place = place;
        this.link = link;
    }

    public Experience(String name, String details) {
        this.name = name;
        this.details = details;
    }

    public Experience(String name, String details, String link) {
        this.name = name;
        this.details = details;
        this.link = link;
    }
}
