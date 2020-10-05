package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.user.UserInformation;
import com.novi.ManageMe.services.user.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // enables cross-origin resource sharing for local port 3000 (with max age before expiring)
@RestController // marks class as a request handler, combines @Controller and @ResponseBody
@RequestMapping("/api/user-information") // class handles requests with this path
public class UserInformationController {

    @Autowired
    UserInformationService userInformationService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String saveUserInformation(@Valid @RequestBody UserInformation userInformation) {
        return this.userInformationService.save(userInformation);
    }

    @PutMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public String updateUserInformation(@Valid @RequestParam String gender, @Valid @RequestParam String place, @Valid @RequestParam Long profilePictureId, @Valid @RequestParam Long userId) {
        return userInformationService.updateUserInformation(gender, place, profilePictureId, userId);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')") // method can be invoked by user with role USER or ADMIN
    public Optional<UserInformation>  getUserInformation(@Valid @RequestParam Long userId) {
        return userInformationService.findByUserId(userId);
    }

}
