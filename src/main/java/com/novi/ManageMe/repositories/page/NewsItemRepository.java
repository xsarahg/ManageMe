package com.novi.ManageMe.repositories.page;

import com.novi.ManageMe.models.page.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE NewsItem n SET n.name = :name, n.description = :description, n.link = :link WHERE n.id = :newsItemId") // set newsItem where newsItemId equals given newsItemId
    void updateNewsItem(@Param("newsItemId") Long newsItemId, @Param("name") String name, @Param("description") String description, @Param("link") String link);
}
