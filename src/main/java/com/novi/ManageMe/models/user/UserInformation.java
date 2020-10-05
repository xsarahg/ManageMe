package com.novi.ManageMe.models.user;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data // generates all the boilerplate for POJOs
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
@Entity // class will be stored in the database
@Table(name = "user_information") // specifies the details of the table that will be used to create the table in the database
public class UserInformation {

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "gender")
    private EGender gender;

    @Column(name = "profile_picture_id")
    private Long profilePictureId;

    @Column(name = "place")
    @Size(max = 30)
    private String place;

    public UserInformation(Long userId, EGender gender, Long profilePictureId, String place) {
        this.userId = userId;
        this.gender = gender;
        this.profilePictureId = profilePictureId;
        this.place = place;
    }

    public UserInformation(Long userId) {
        this.userId = userId;
    }
}
