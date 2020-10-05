package com.novi.ManageMe.repositories.profile;

import com.novi.ManageMe.models.profile.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {

    @Query("SELECT u FROM Quote u WHERE u.category = :category OR u.category IS NULL") // if category = null this means the quote is for all categories
    List<Quote> findByCategory(@Param("category") String category);

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE Quote q SET q.text = :text, q.author = :author, q.category = :category WHERE q.id = :quoteId")
    void updateQuote(@Param("quoteId") Long quoteId, @Param("text") String text, @Param("author") String author, @Param("category") String category);
}
