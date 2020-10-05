package com.novi.ManageMe.controllers;

import com.novi.ManageMe.models.user.*;
import com.novi.ManageMe.payloads.user.request.LoginRequest;
import com.novi.ManageMe.payloads.user.request.SignupRequest;
import com.novi.ManageMe.payloads.user.response.JwtResponse;
import com.novi.ManageMe.payloads.user.response.MessageResponse;
import com.novi.ManageMe.repositories.user.CategoryRepository;
import com.novi.ManageMe.repositories.user.RoleRepository;
import com.novi.ManageMe.repositories.user.UserRepository;
import com.novi.ManageMe.security.jwt.JwtUtils;
import com.novi.ManageMe.security.services.UserDetailsImpl;
import com.novi.ManageMe.services.roadmap.FaseService;
import com.novi.ManageMe.services.roadmap.RoadmapService;
import com.novi.ManageMe.services.roadmap.RoadmapServiceImpl;
import com.novi.ManageMe.services.templates.RoadmapTemplateService;
import com.novi.ManageMe.services.user.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

// change origin when application is live
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600) // enables cross-origin resource sharing for local port 3000 (with max age before expiring)
@RestController // marks class as a request handler, combines @Controller and @ResponseBody
@RequestMapping("/api/auth") // class handles requests with this path
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RoadmapService roadmapService;

    @Autowired
    FaseService faseService;

    @Autowired
    RoadmapServiceImpl roadmapServiceImpl;

    @Autowired
    RoadmapTemplateService roadmapTemplateService;

    @Autowired
    UserInformationService userInformationService;


    @PostMapping("/login") //method handles POST requests with this path (login)
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) { // validates the LoginRequest parameter, which is provided in the body of the request

        // authenticates the username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        // update the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // generate JWT token
        String jwt = jwtUtils.generateJwtToken(authentication);

        // get UserDetails
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // return response with JWT token and UserDetails
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                userDetails.getCategory()
                ));
    }

    @PostMapping("/sign-up") //method handles POST requests with this path (signup)
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) { // validates the SignupRequest parameter, which is provided in the body of the request

        // checks if username is taken
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            // if so, throw 400 status and error message
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        // checks is email is already in use
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            // if so, throw 400 status and error message
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword())); // save password encoded

        // create objects based on user category
        String category = signUpRequest.getCategory(); // get category from signUpRequest
        // for now there is just one category. When there are more categories, "category.equals("") should be taken out of the first if-statement.
        // when there are are a number of categories, change if-statements to switch statement.
        if (category.equals("artist") || category.equals("")) {
            // set user category
            Category userCategory = categoryRepository.findByName(ECategory.CATEGORY_ARTIST)
                    .orElseThrow(() -> new RuntimeException("Error: Category is not found"));
            user.setCategory(userCategory);
        }

        boolean typeUser = false;
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();


        // if no role is defined, set ROLE_USER
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            typeUser = true;

        // else, set roles as provided by SignupRequest
        } else {
            for (String role : strRoles) {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "business":
                        Role businessRole = roleRepository.findByName(ERole.ROLE_BUSINESS)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(businessRole);

                        break;

                    case "media":
                        Role mediaRole = roleRepository.findByName(ERole.ROLE_MEDIA)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(mediaRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                        typeUser = true;

                }
            }
        }

        // set roles
        user.setRoles(roles);

        // save user
        userRepository.save(user);

        // if user has type USER, create roadmap
        if (typeUser) {
            roadmapServiceImpl.createInitialRoadmap(user); // at first, all the code for creating the roadmap was placed here. Decided to use e roadmapServiceImpl class to do this to keep the controller as clean as possible
        }

        // save userInformation
        UserInformation userInformation = new UserInformation(user.getId());
        userInformationService.save(userInformation);

        // return status 200 and success message
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}