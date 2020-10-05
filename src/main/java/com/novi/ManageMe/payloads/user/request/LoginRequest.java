package com.novi.ManageMe.payloads.user.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank // ensures the property is not blank
    private String username;

    @NotBlank // ensures the property is not blank
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}