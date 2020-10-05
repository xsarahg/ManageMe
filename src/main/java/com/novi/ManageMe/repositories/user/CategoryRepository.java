package com.novi.ManageMe.repositories.user;

import com.novi.ManageMe.models.user.Category;
import com.novi.ManageMe.models.user.ECategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(ECategory category);

}
