package com.novi.ManageMe.models.user;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "categories") // specifies the details of the table that will be used to create the table in the database
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GenerationType.IDENTITY is necessary because the next available value has to be put automatically
    // GenerationType.AUTO does not work and will throw an error because id cannot be null
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.STRING) // enumerated value
    @Column(name = "name")
    private ECategory name;

    public Category(ECategory name) {
        this.name = name;
    }
}