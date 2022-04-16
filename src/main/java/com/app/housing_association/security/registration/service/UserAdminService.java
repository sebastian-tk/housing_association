package com.app.housing_association.security.registration.service;

import com.app.housing_association.security.registration.model.AdminRegistrationData;
import com.app.housing_association.user.entity.User;
import com.app.housing_association.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.app.housing_association.user.entity.enums.Role.ADMIN;

@Service
public class UserAdminService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserAdminService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<User> registration(AdminRegistrationData adminRegistrationData) {
        if (adminRegistrationData == null) {
            throw new IllegalArgumentException("Data to registration cannot be null");
        }
        if (userRepository.findByUsernameIgnoreCase(adminRegistrationData.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Account already exist");
        }
        User user = new User();
        user.setUsername(adminRegistrationData.getUsername());
        user.setEmail(adminRegistrationData.getEmail());
        user.setPassword(encryptPassword(adminRegistrationData.getPassword()));
        user.setRole(ADMIN);
        return Optional.of(userRepository.save(user));
    }

    private String encryptPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
