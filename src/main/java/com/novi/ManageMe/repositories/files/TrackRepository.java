package com.novi.ManageMe.repositories.files;

import com.novi.ManageMe.models.files.Track;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface TrackRepository extends FileModelBaseRepository<Track> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Track t SET t.name = :name WHERE t.id = :trackId")
    void updateName(@Param("trackId") Long trackId, @Param("name") String name);

}
