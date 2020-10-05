package com.novi.ManageMe.repositories.roadmap;

import com.novi.ManageMe.models.roadmap.Roadmap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface RoadmapRepository extends JpaRepository<Roadmap, Long> {

    Optional<Roadmap> findById(Long id);

    @Query("SELECT r FROM Roadmap r WHERE r.user_id = :userId")
    Optional<Roadmap> findByUserId(@Param("userId") Long userId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Roadmap r SET r.percentageDone = :percentageDone WHERE r.id = :roadmapId")
    void updatePercentageDone(@Param("percentageDone") int percentageDone, @Param("roadmapId") Long roadmapId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Roadmap r SET r.done = true WHERE r.id = :roadmapId")
    void updateDone(@Param("roadmapId") Long roadmapId);
}