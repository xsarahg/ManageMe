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
@Table(name = "TEMPLATE_roadmaps") // specifies the details of the table that will be used to create the table in the database
public class RoadmapTemplate extends Template{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 50)
    private String name;

    @Column(name = "fases_amount")
    private int num_of_fases;

    @ManyToMany(fetch = FetchType.LAZY) // creates many-to-many relationship between the User and Role entity, only fetches when it is required
    @JoinTable(	name = "TEMPLATE_roadmaps_fases",
            joinColumns = @JoinColumn(name = "roadmap_template_id"),
            inverseJoinColumns = @JoinColumn(name = "fase_template_id")) // specifies the columns used for joining the entities
    private Set<FaseTemplate> fases = new HashSet<>();

    public RoadmapTemplate(String title, String description, int num_of_fases) {
        super(title, description);
        this.num_of_fases = num_of_fases;
    }
}
