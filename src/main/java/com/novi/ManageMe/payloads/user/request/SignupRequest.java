package com.novi.ManageMe.payloads.user.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


public class SignupRequest {
    @NotBlank // ensures the property is not blank
    @Size(min = 2, max = 20) // validates the property has a min size of 2 and a max size of 20
    private String username;

    @NotBlank // ensures the property is not blank
    @Size(max = 50) // validates the property has a max size of 50
    @Email // validates that the property is a valid email address
    private String email;

    private Set<String> roles;

    @NotBlank // ensures the property is not blank
    @Size(min = 6, max = 40) // validates the property has a min size of 6 and a max size of 40
    private String password;

    private String category;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public void setRole(Set<String> roles) {
        this.roles = roles;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
