package com.novi.ManageMe.repositories.profile;

import com.novi.ManageMe.models.profile.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

    @Query("SELECT t FROM Tutorial t WHERE t.category = :category OR t.category IS NULL") // if category = null this means the tutorial is for all categories
    List<Tutorial> findByCategory(@Param("category") String category);

    // need to add () so the query will work properly
    @Query("SELECT t FROM Tutorial t WHERE (t.category = :category OR t.category IS NULL) AND t.title LIKE %:searchString%") // find category that contains a certain category and which title contains a certain name
    List<Tutorial> findByCategoryAndName(@Param("category") String category, @Param("searchString") String searchString);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Tutorial t SET t.title = :title, t.description = :description, t.category = :category, t.link = :link WHERE t.id = :tutorialId") // set tutorial where tutorialId equals given tutorialId
    void updateTutorial(@Param("tutorialId") Long tutorialId, @Param("title") String title, @Param("description") String description, @Param("category") String category, @Param("link") String link);
}
