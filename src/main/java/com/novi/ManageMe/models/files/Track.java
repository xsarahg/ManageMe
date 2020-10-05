package com.novi.ManageMe.models.files;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "tracks") // specifies the details of the table that will be used to create the table in the database
public class Track extends FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Size(max = 30)
    private String name;

    public Track(Long userId, String name, String description, String fileName, String fileType, byte[] data) {
        super(userId, description, fileName, fileType, data);
        this.name = name;
    }
}
