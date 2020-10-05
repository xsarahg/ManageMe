package com.novi.ManageMe.repositories.user;

import com.novi.ManageMe.models.user.EGender;
import com.novi.ManageMe.models.user.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.Optional;

public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE UserInformation u SET u.gender = :gender, u.profilePictureId = :profilePictureId, u.place = :place WHERE u.userId = :userId") // set userInformation where userId equals given userId
    void updateUserInformation(@Param("gender") EGender gender, @Param("profilePictureId") Long profilePictureId, @Param("place") String place, @Param("userId") Long userId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE UserInformation u SET u.profilePictureId = :profilePictureId WHERE u.userId = :userId")
    void updateProfilePictureId(@Param("userId") Long userId, @Param("profilePictureId") Long profilePictureId);

    @Query("SELECT u FROM UserInformation u WHERE u.profilePictureId = :photoId")
    Optional<UserInformation> findByProfilePictureId(@Param("photoId") Long photoId);
}
