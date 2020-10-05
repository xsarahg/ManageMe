package com.novi.ManageMe.repositories.files;

import com.novi.ManageMe.models.files.Photo;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PhotoRepository extends FileModelBaseRepository<Photo> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Photo p SET p.profilePicture = :b WHERE p.id = :photoId")
    void updateProfilePictureById(@Param("photoId") Long photoId, @Param("b") boolean b);
}
