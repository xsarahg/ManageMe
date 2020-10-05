package com.novi.ManageMe.repositories.page;

import com.novi.ManageMe.models.page.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Experience e SET e.name = :name, e.details = :details, e.institution = :institution, e.place = :place, e.link = :link WHERE e.id = :experienceId") // set experience where experienceId equals given exprienceId
    void updateExperience(@Param("experienceId") Long experienceeId, @Param("name") String name, @Param("details") String details, @Param("institution") String institution, @Param("place") String place, @Param("link") String link);
}


