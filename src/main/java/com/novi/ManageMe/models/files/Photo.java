package com.novi.ManageMe.models.files;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "photos") // specifies the details of the table that will be used to create the table in the database
public class Photo extends FileModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "profile_picture")
    private boolean profilePicture;

    public Photo(Long userId, String description, String fileName, String fileType, byte[] data, boolean profilePicture) {
        super(userId, description, fileName, fileType, data);
        this.profilePicture = profilePicture;
    }
}
