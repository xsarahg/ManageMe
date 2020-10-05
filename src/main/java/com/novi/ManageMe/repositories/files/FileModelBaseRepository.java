package com.novi.ManageMe.repositories.files;

import com.novi.ManageMe.models.files.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

// all of the following methods will be available in the other fileModel repositories
@NoRepositoryBean // makes it not possible for this repository to be instantiated -- read-only
public interface FileModelBaseRepository <F extends FileModel> extends JpaRepository<F, Long> {

    @Transactional // we need to use @Transactional because a large object can be stored in several records and we need it to access the database
    @Query("SELECT f FROM #{#entityName} f WHERE f.userId = :userId") // query that selects all files with the given userId
    List<F> findFileByUserId(@Param("userId") Long userId); // returns a list of files

    @Transactional // let's all statements be executed on readonly data
    @Modifying // lets query  execute INSERT, UPDATE, DELETE and DDL queries
    @Query("UPDATE #{#entityName} f SET f.description = :description WHERE f.id = :fileId") // query that updates the descriptopn of a file
    void updateDescription(@Param("fileId") Long fileId, @Param("description") String description);

}
