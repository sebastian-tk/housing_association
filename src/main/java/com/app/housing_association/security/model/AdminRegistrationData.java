package com.app.housing_association.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminRegistrationData {

    private String firstname;
    private String lastname;
    private String phoneNumber;
    private String username;
    private String email;
    private String password;
}
