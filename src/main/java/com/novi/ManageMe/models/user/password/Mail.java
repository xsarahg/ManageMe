package com.novi.ManageMe.models.user.password;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Map;

@Getter // lombok shortcut for adding getters
@Setter // lombok shortcut for adding setters
@NoArgsConstructor // lombok shortcut for a constructor without any arguments
public class Mail {

    @Email
    private String from;

    @Email
    private String to;

    @Size(max = 20)
    private String subject;

    private Map<String, Object> model;
}
