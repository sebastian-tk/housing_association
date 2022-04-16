package com.app.housing_association.security.registration.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AdminRegistrationData {
    private String username;
    private String email;
    private String password;
}
