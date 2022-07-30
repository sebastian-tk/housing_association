package com.app.housing_association.security.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import static com.app.housing_association.common.utils.IValidation.USER_PASSWORD_NULL_VALIDATION;
import static org.apache.logging.log4j.util.Strings.isBlank;

@RequiredArgsConstructor
@Component
public class PasswordUtils {

    private final PasswordEncoder passwordEncoder;

    public String encryptPassword(String password) {
        if(isBlank(password)){
            throw new IllegalArgumentException(USER_PASSWORD_NULL_VALIDATION);
        }
        return passwordEncoder.encode(password);
    }

    public boolean isMatchesPassword(String password, String hash) {
        if(isBlank(password)){
            throw new IllegalArgumentException(USER_PASSWORD_NULL_VALIDATION);
        }
        return passwordEncoder.matches(password, hash);
    }
}
