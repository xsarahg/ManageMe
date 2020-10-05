package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.user.*;
import com.novi.ManageMe.models.user.password.Mail;
import com.novi.ManageMe.models.user.password.PasswordResetToken;
import com.novi.ManageMe.security.services.UserDetailsServiceImpl;
import com.novi.ManageMe.services.user.EmailService;
import com.novi.ManageMe.services.user.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // enables cross-origin resource sharing for local port 3000 (with max age before expiring)
@RestController // marks class as a request handler, combines @Controller and @ResponseBody
@RequestMapping("/api/auth/password") // class handles requests with this path
public class PasswordForgotController {

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder encoder;

    // no authorization is needed
    @PostMapping(path = "/reset") // method handles POST requests with this path (reset password)
    public String handlePasswordReset(@Valid @RequestParam String token, @Valid @RequestParam String password) {

        try {
            // find rPasswordResetToken by token
            PasswordResetToken resetToken = passwordResetTokenService.findByToken(token);

            // check if token is expired
            if (resetToken.isExpired()) {
                return "Token is expired, try again!";
            }

            // find user
            User user = resetToken.getUser();

            // encode password
            String updatedPassword = encoder.encode(password);

            // update user's password
            user.setPassword(updatedPassword);
            userService.updatePassword(updatedPassword, user.getId());

            // delete token
            passwordResetTokenService.delete(resetToken);

            return "You have just changed your password, congrats!";
        } catch (Exception e) {
            return "Nope, did not work";
        }
    }

    // no authorization is needed
    @PostMapping // method handles POST requests (request email with link for resetting password)
    public String processForgotPassword(@Valid @Email @RequestParam String email) {

        // find user by email
        User user = userService.findByEmail(email);

        // if no user is found
        if (user == null) {
            return ("No user found with this email");
        }

        // create PasswordResetToken
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        passwordResetTokenService.save(token);

        // create mail
        Mail mail = new Mail();
        mail.setFrom("mm.manageme@gmail.com");
        mail.setTo(user.getEmail());
        mail.setSubject("Password reset");

        // create model
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        String url = "http://localhost:3000/api/auth/password/"; // WHEN THE APPLICATION IS LIVE, WE CAN PUT OUR URL HERE!
        model.put("resetUrl", url + token.getToken()); // removed "/reset?token="
        mail.setModel(model);
        emailService.sendEmail(mail);

        return "Mail has been sent!";
    }
}
