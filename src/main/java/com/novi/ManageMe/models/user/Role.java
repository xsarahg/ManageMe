package com.novi.ManageMe.models.user;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "roles") // specifies the details of the table that will be used to create the table in the database
public class Role {

    @Id // specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the value of the primary key
    @Column(name = "id")
    private Integer id; // using int instead of Long because there will just be a few roles

    @Enumerated(EnumType.STRING) // declares field is enumerated (type String)
    @Column(name = "name")
    private ERole name;

    public Role(ERole name) {
        this.name = name;
    }
}