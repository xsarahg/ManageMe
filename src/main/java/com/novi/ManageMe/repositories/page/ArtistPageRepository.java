package com.novi.ManageMe.repositories.page;

import com.novi.ManageMe.models.page.ArtistPage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional // implements the PageBaseReposity
public interface ArtistPageRepository extends PageBaseRepository<ArtistPage> {

    // BIOGRAPHY //
    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE ArtistPage a SET a.biography = :biography WHERE a.id = :userId")
    void setBiography(@Param("biography") String biography, @Param("userId") Long userId);


    // GENRE //
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "INSERT INTO artist_page_genres(artist_page_id, genre_id) VALUES (:artistPageId, :genreId)", // query for inserting values in artist_page_genres
            nativeQuery = true) // ??
    void addGenre(@Param("genreId") Long genreId, @Param("artistPageId") Long artistPageId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "DELETE FROM artist_page_genres WHERE genre_id = :genreId",
            nativeQuery = true)
    void deleteGenreById(@Param("genreId") Long genreId);



    // EXPERIENCE //
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "INSERT INTO artist_page_experiences(artist_page_id, experience_id) VALUES (:artistPageId, :experienceId)", // query for inserting values in artist_page_experiences
            nativeQuery = true)
    void addExperience(@Param("experienceId") Long experienceId, @Param("artistPageId") Long artistPageId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "DELETE FROM artist_page_experiences WHERE experience_id = :experienceId",
            nativeQuery = true)
    void deleteExperienceById(@Param("experienceId") Long experienceId);



    // NEWS ITEM //
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "INSERT INTO artist_page_news(artist_page_id, news_item_id) VALUES (:artistPageId, :newsItemId)", // query for inserting values in artist_page_news
            nativeQuery = true)
    void addNewsItem(@Param("newsItemId") Long newsItemId, @Param("artistPageId") Long artistPageId);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "DELETE FROM artist_page_news WHERE news_item_id = :newsItemId",
            nativeQuery = true)
    void deleteNewsItemById(@Param("newsItemId") Long newsItemId);



    // TRACK //
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "INSERT INTO artist_page_tracks(artist_page_id, track_id) VALUES (:artistPageId, :trackId)",
            nativeQuery = true)
    void addTrackId(@Param("trackId") Long trackId, @Param("artistPageId") Long artistPageId);



    // PHOTO //
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query(value = "INSERT INTO artist_page_photos(artist_page_id, photo_id) VALUES (:artistPageId, :photoId)", // query for inserting values in artist_page_photos
            nativeQuery = true)
    void addPhotoId(@Param("photoId") Long photoId, @Param("artistPageId") Long artistPageId);
}
