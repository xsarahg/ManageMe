package com.novi.ManageMe.repositories.user;

import com.novi.ManageMe.models.user.password.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // declares this interface as a repository for persisting and accessing data
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    // the JpaRepository provides a standard way of persisting objects into the database

    PasswordResetToken findByToken(String token);
}
