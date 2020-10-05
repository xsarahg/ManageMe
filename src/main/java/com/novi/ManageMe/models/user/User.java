package com.novi.ManageMe.models.user;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.novi.ManageMe.models.files.Photo;
import com.novi.ManageMe.models.page.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(	name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        }) // specifies the details of the table that will be used to create the table in the database
public class User {

    @Id // specifies the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // provides the specification of generation strategies for the value of the primary key
    private Long id;

    @NotBlank // validates that the property is not blank
    @Size(max = 20) // validates the property has a max size of 20
    private String username;

    @NotBlank // ensures the property is not blank
    @Size(max = 50) // validates the property has a max size of 50
    @Email // validates that the property is a valid email address
    private String email;

    @NotBlank // ensures the property is not blank
    @Size(max = 120) // validates the property has a max size of 120
    private String password;

    @ManyToMany(fetch = FetchType.LAZY) // creates many-to-many relationship between the User and Role entity, only fetches when it is required
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")) // specifies the columns used for joining the entities
    private Set<Role> roles = new HashSet<>();
    // this way a user can have more than 1 role

    // not more than 1 category possible
    @ManyToOne
    private Category category;

    // since id is a generated value, we do not have to place it in the constructor
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
}