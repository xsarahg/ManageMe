package com.novi.ManageMe.models.page;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "news_items") // specifies the details of the table that will be used to create the table in the database
public class NewsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "description")
    @Size(max = 200)
    private String description;

    @Column(name = "link")
    @Size(max = 100)
    private String link;

    public NewsItem(String name, String description, String link) {
        this.name = name;
        this.description = description;
        this.link = link;
    }

    public NewsItem(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
