package com.novi.ManageMe.repositories.page;

import com.novi.ManageMe.models.page.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// all of the following methods will be available in the other page repositories
@NoRepositoryBean // makes it not possible for this repository to be instantiated -- read-only
public interface PageBaseRepository <P extends Page> extends JpaRepository<P, Long> {

    @Query("SELECT p FROM #{#entityName} p WHERE p.user_id = :userId") // query for searching Page with certain user_id
    Optional<P> findByUser_id(@Param("userId") Long userId);

}
