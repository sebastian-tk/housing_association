package com.app.housing_association.security.service;

import com.app.housing_association.security.model.AdminRegistrationData;
import com.app.housing_association.security.utils.PasswordUtils;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.app.housing_association.common.utils.IValidation.REGISTRATION_ACCOUNT_EXIST;
import static com.app.housing_association.common.utils.IValidation.REGISTRATION_NULL_VALIDATION;
import static com.app.housing_association.user.entity.enums.Role.ADMIN;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordUtils passwordUtils;

    public RegistrationService(UserRepository userRepository, PasswordUtils passwordUtils) {
        this.userRepository = userRepository;
        this.passwordUtils = passwordUtils;
    }

    public Optional<User> registration(AdminRegistrationData adminRegistrationData) {
        if (adminRegistrationData == null) {
            throw new IllegalArgumentException(REGISTRATION_NULL_VALIDATION);
        }
        if (userRepository.findByUsernameIgnoreCase(adminRegistrationData.getUsername()).isPresent()) {
            throw new IllegalArgumentException(REGISTRATION_ACCOUNT_EXIST);
        }
        User user = new User();
        user.setFirstname(adminRegistrationData.getFirstname());
        user.setLastname(adminRegistrationData.getLastname());
        user.setPhoneNumber(adminRegistrationData.getPhoneNumber());
        user.setUsername(adminRegistrationData.getUsername());
        user.setEmail(adminRegistrationData.getEmail());
        user.setPassword(passwordUtils.encryptPassword(adminRegistrationData.getPassword()));
        user.setRole(ADMIN);
        return Optional.of(userRepository.save(user));
    }
}
