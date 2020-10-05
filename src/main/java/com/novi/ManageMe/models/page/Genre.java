package com.novi.ManageMe.models.page;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "genres") // specifies the details of the table that will be used to create the table in the database
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 30)
    private String name;

    @Column(name = "description")
    @Size(max = 100)
    private String description;

    @Column(name = "rating")
    private int rating;

    public Genre(String name, String description, int rating) {
        this.name = name;
        this.description = description;
        this.rating = rating;
    }
}
