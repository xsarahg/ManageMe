package com.novi.ManageMe.models.templates;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "TEMPLATE_subfases") // specifies the details of the table that will be used to create the table in the database
public class SubFaseTemplate extends Template{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "condition")
    @Size(max = 50)
    private String condition;

    @Column(name = "fase_template_id")
    private Long fase_template_id;

    public SubFaseTemplate(String title, String description, String condition, Long fase_template_id) {
        super(title, description);
        this.condition = condition;
        this.fase_template_id = fase_template_id;
    }
}
