package com.novi.ManageMe.models.templates;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "TEMPLATE_fases") // specifies the details of the table that will be used to create the table in the database
public class FaseTemplate extends Template{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "subfases_amount")
    private int num_of_subfases;

    @Column(name = "roadmap_template_id")
    private Long roadmap_template_id;

    @ManyToMany(fetch = FetchType.LAZY) // creates many-to-many relationship between the User and Role entity, only fetches when it is required
    @JoinTable(	name = "TEMPLATE_fases_subfases",
            joinColumns = @JoinColumn(name = "fase_template_id"),
            inverseJoinColumns = @JoinColumn(name = "subfase_template_id")) // specifies the columns used for joining the entities
    private Set<SubFaseTemplate> subfases = new HashSet<>();

    public FaseTemplate(String title, String description, int num_of_subfases, Long roadmap_template_id) {
        super(title, description);
        this.num_of_subfases = num_of_subfases;
        this.roadmap_template_id = roadmap_template_id;
    }
}
