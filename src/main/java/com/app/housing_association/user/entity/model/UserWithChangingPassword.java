package com.app.housing_association.user.entity.model;

import com.app.housing_association.user.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWithChangingPassword extends User {
    private String newPassword;

    public UserWithChangingPassword(User user) {
        setFirstname(user.getFirstname());
        setLastname(user.getLastname());
        setUsername(user.getUsername());
        setPhoneNumber(user.getPhoneNumber());
        setRole(user.getRole());
        setEmail(user.getEmail());
        setPassword(user.getPassword());
    }
}
