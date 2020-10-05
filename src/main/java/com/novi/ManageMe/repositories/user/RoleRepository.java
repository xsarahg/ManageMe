package com.novi.ManageMe.repositories.user;

import com.novi.ManageMe.models.user.ERole;
import com.novi.ManageMe.models.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository // declares this interface as a repository for persisting and accessing data
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // the JpaRepository provides a standard way of persisting objects into the database

    Optional<Role> findByName(ERole name);

}