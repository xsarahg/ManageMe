package com.novi.ManageMe.repositories.roadmap;

import com.novi.ManageMe.models.roadmap.Fase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface FaseRepository extends JpaRepository<Fase, Long> {

    @Override
    Optional<Fase> findById(Long id);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Fase f SET f.percentageDone = :percentageDone WHERE f.id = :faseId")
    void updatePercentageDone(@Param("percentageDone") int percentageDone, @Param("faseId") Long faseId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Fase f SET f.done = true WHERE f.id = :faseId")
    void updateDone(@Param("faseId") Long faseId);
}
