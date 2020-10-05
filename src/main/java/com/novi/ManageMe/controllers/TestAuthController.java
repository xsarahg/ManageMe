package com.novi.ManageMe.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// this class is used for testing authorization in our application
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // enables cross-origin resource sharing for all ports (with max age before expiring)
@RestController // marks class as a request handler, combines @Controller and @ResponseBody
@RequestMapping("/api/test") // class handles requests with this path
public class TestAuthController {

    @GetMapping("/all") //method handles GET requests with this path (test access for everyone)
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user") //method handles GET requests with this path (test access for all users)
    @PreAuthorize("hasRole('USER')") // checks if user has role USER, MODERATOR or ADMIN before entering the method
    public String userAccess() {
        return "User content.";
    }

    @GetMapping("/business") //method handles GET requests with this path (test access for users with role BUSINESS)
    @PreAuthorize("hasRole('BUSINESS')") // checks if user has role BUSINESS before entering the method
    public String businessAccess() {
        return "Business content.";
    }

    @GetMapping("/media") //method handles GET requests with this path (test access for users with role MEDIA)
    @PreAuthorize("hasRole('MEDIA')") // checks if user has role MEDIA before entering the method
    public String mediaAccess() {
        return "Media content.";
    }

    @GetMapping("/admin") //method handles GET requests with this path (test access for users with role ADMIN)
    @PreAuthorize("hasRole('ADMIN')") // checks if user has role MODERATOR before entering the method
    public String adminAccess() {
        return "Admin content.";
    }
}
