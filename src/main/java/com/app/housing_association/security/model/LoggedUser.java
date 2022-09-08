package com.app.housing_association.security.model;

import com.app.housing_association.user.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoggedUser {
    private Long id;
    private String username;
    private Role role;
    private String token;
}
