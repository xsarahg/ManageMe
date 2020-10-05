package com.novi.ManageMe.repositories.page;

import com.novi.ManageMe.models.page.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Genre g SET g.name = :name, g.description = :description, g.rating = :rating WHERE g.id = :genreId") // set genre where genreId equals given genreId
    void updateGenre(@Param("genreId") Long genreId, @Param("name") String name, @Param("description") String description, @Param("rating") int rating);
}
