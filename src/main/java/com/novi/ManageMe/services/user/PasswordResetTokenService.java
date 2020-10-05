package com.novi.ManageMe.services.user;

import com.novi.ManageMe.models.user.password.PasswordResetToken;
import com.novi.ManageMe.repositories.user.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenService {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    public PasswordResetTokenService(PasswordResetTokenRepository passwordResetTokenRepository) {
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    // find PasswordResetToken by token
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    // save PasswordResetToken
    public void save(PasswordResetToken token) {
        passwordResetTokenRepository.save(token);
    }

    // delete PasswordResetToken
    public void delete(PasswordResetToken token) {
        passwordResetTokenRepository.delete(token);
    }
}
