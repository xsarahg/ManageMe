package com.novi.ManageMe.models.profile;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "tutorials")
public class Tutorial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "link", unique = true)
    @Size(max = 1000)
    private String link;

    @Column(name = "title")
    @Size(max = 100)
    private String title;

    @Column(name = "description")
    @Size(max = 1000)
    private String description;

    @Column(name = "category")
    @Size(max = 50)
    private String category;

    public Tutorial(String link, String title, String description) {
        this.link = link;
        this.title = title;
        this.description = description;
    }

    public Tutorial(String link, String title, String description, String category) {
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
    }
}
