package com.novi.ManageMe.repositories.roadmap;

import com.novi.ManageMe.models.roadmap.SubFase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface SubFaseRepository extends JpaRepository<SubFase, Long> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE SubFase s SET s.done = true WHERE s.id = :subFaseId")
    void updateDone(@Param("subFaseId") Long subFaseId);
}
