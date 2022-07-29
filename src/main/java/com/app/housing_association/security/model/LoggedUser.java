package com.app.housing_association.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoggedUser {
    private Long id;
    private String username;
    private String email;
    private String token;
}
