package com.novi.ManageMe.models.profile;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "quotes") // specifies the details of the table that will be used to create the table in the database
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text", unique = true)
    @Size(max = 500)
    private String text;

    @Column(name = "author")
    @Size(max = 50)
    private String author;

    @Column(name = "category")
    @Size(max = 30)
    private String category;

    public Quote(String text, String author) {
        this.text = text;
        this.author = author;
    }

    public Quote(String text, String author, String category) {
        this.text = text;
        this.author = author;
        this.category = category;
    }
}
